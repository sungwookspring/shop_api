package com.shop.ShoppingMall.api;

import com.shop.ShoppingMall.Repository.OrderRepository;
import com.shop.ShoppingMall.Repository.OrderSearch;
import com.shop.ShoppingMall.domain.Address;
import com.shop.ShoppingMall.domain.Order;
import com.shop.ShoppingMall.domain.OrderStatus;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class OrderSimpleAPI_Controller {
    private final OrderRepository orderRepository;

    @GetMapping("/api/v1/simple-orders")
    public List<Order> ordersV1(){
        List<Order> all = orderRepository.findAllByString(new OrderSearch());
        return all;
    }

    @GetMapping("/api/v2/simple-orders")
    public List<SimpleOrderDto> ordersV2(){
        //1+N 문제 -> 1개를 구하기 위해 N개의 쿼리문 실행(*반복문 횟수)
        List<Order> orders = orderRepository.findAllByString(new OrderSearch());
        return orders.stream()
                    .map(order -> new SimpleOrderDto(order))
                    .collect(Collectors.toList());
    }

    @GetMapping("/api/v3/simple-orders")
    public List<SimpleOrderDto> ordersV3(){
        List<Order> orders = orderRepository.findAllWithMemberDelivery();
        return orders.stream().map(order -> new SimpleOrderDto(order))
                    .collect(Collectors.toList());
    }

    @Data
    static class SimpleOrderDto{
        private Long orderId;
        private String name;
        private LocalDateTime orderDate;
        private OrderStatus orderStatus;
        private Address address;

        public SimpleOrderDto(Order order) {
            this.orderId = order.getId();
            this.name = order.getMember().getName();
            this.orderDate = order.getOrderDate();
            this.orderStatus = order.getStatus();
            this.address = order.getDelivery().getAddress();
        }
    }

}
