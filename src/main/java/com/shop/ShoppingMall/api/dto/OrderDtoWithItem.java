package com.shop.ShoppingMall.api.dto;

import com.shop.ShoppingMall.domain.Address;
import com.shop.ShoppingMall.domain.Order;
import com.shop.ShoppingMall.domain.OrderStatus;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class OrderDtoWithItem {
    private Long orderId;
    private String name;
    private LocalDateTime orderDate;
    private OrderStatus orderStatus;
    private Address address;
    private List<OrderItemsDto> orderItemsDtos;

    public OrderDtoWithItem(Order order) {
        this.orderId = order.getId();
        this.name = order.getMember().getName();
        this.orderDate = order.getOrderDate();
        this.orderStatus = order.getStatus();
        this.address = order.getDelivery().getAddress();
        orderItemsDtos = order.getOrderitems().stream()
                .map(orderItem -> new OrderItemsDto(orderItem.getItem().getName(), orderItem.getItem().getPrice()))
                .collect(Collectors.toList());
    }
}
