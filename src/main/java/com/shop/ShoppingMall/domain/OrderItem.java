package com.shop.ShoppingMall.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.shop.ShoppingMall.domain.item.Item;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class OrderItem {

    @Id @GeneratedValue
    @Column(name = "order_item_id")
    private Long id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    private int OrderPrice;

    private int count;

    /***
     * 생성 메소드
     */

    /***
     * setter로 주문하는 것을 금지
     */
    protected OrderItem() {
    }

    public static OrderItem createOrderItem(Item item, int orderPrice, int count){
        OrderItem orderItem = new OrderItem();
        orderItem.setItem(item);
        orderItem.setOrderPrice(orderPrice);
        orderItem.setCount(count);

        //주문한 갯수만큼 재고 감소
        item.removestock(count);

        return orderItem;
    }
    
    
    /***
     * 비지니스 로직
     */

    /***
     * 주문 취소
     */
    public void cancel() {
        //재고 수량을 주문 횟수만큼 증가
        getItem().addstock(count);
    }

    /***
     * 전제 주문 가격
     * @return
     */
    public int getTotalPrice() {
        return getOrderPrice() * getCount();
    }
}
