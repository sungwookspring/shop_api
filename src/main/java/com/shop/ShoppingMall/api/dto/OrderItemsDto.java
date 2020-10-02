package com.shop.ShoppingMall.api.dto;

import lombok.Getter;

@Getter
public class OrderItemsDto {
    private String name;
    private int price;

    public OrderItemsDto(String name, int price) {
        this.name = name;
        this.price = price;
    }
}
