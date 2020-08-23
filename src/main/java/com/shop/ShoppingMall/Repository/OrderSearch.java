package com.shop.ShoppingMall.Repository;

import com.shop.ShoppingMall.domain.OrderStatus;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class OrderSearch {
    private String memberName;
    private OrderStatus orderStatus;
}
