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

    // member - order 연관관계
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
}
