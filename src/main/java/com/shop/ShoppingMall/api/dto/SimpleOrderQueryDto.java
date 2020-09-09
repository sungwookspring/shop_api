package com.shop.ShoppingMall.api.dto;

import com.shop.ShoppingMall.domain.Address;
import com.shop.ShoppingMall.domain.Order;
import com.shop.ShoppingMall.domain.OrderStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SimpleOrderQueryDto {
    private Long orderId;
    private String name;
    private LocalDateTime orderDate;
    private OrderStatus orderStatus;
    private Address address;

    public SimpleOrderQueryDto(Long id, String name, LocalDateTime orderDate,
                               OrderStatus orderStatus, Address address) {
        this.orderId = id;
        this.name = name;
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
        this.address = address;
    }
}
