package com.shop.ShoppingMall.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@Table(name="orders")
public class Order {

    @Id @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    List<OrderItem> orderitems = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    /*
        연관관계 메서드
        (양방향관계 설정)

        메소드 설정 전
        member1 = new Member()
        order = new Order()

        member1.getOrder().add(order)
        order.setMember(member1)
     */

    // member - order 연관관계 메소드
    public void setMember(Member member){
        this.member = member;
        //양방향 설정
        member.getOrders().add(this);
    }

    //order - orderitem
    public void addOrderItem(OrderItem orderitem){
        orderitems.add(orderitem);
        orderitem.setOrder(this);
    }

    public void setDelivery(Delivery delivery){
        this.delivery = delivery;
        delivery.setOrder(this);
    }

    /***
     * 주문 생성자 메소드(모든 연관관계 설정 후 order객체 리턴)
     */
    public static Order createOrder(Member member, Delivery delivery, OrderItem... orderItems){
        //주문 객체 초기화
        Order order = new Order();
        order.setMember(member);
        order.setDelivery(delivery);

        for(OrderItem orderItem: orderItems){
            order.addOrderItem(orderItem);
        }

        //배송상태 설정
        order.setStatus(OrderStatus.ORDER);
        order.setOrderDate(LocalDateTime.now());

        return order;
    }

    /***
     * 비지니스 로직
     */

    /***
     * 주문취소
     */
    public void cancel(){
        // 이미 배송 중이면 주문취소 불가
        if(delivery.getStatus() == DeliveryStatus.COMPLETED){
            throw new IllegalStateException("이미 배송 완료된 주문은 취소가 불가능합니다");
        }

        this.setStatus(OrderStatus.CANCLE);
        for(OrderItem orderItem : orderitems){
            orderItem.cancel();
        }
    }

    /***
     * 아이템 전체 주문 가격
     * @return
     */
    public int getTotalPrice(){
        int totalPrice = 0;
        for(OrderItem orderItem: orderitems){
            totalPrice += orderItem.getTotalPrice();
        }

        return totalPrice;
    }
    
}
