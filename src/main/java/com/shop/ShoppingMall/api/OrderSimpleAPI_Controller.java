package com.shop.ShoppingMall.api;

import com.shop.ShoppingMall.Repository.OrderRepository;
import com.shop.ShoppingMall.Repository.OrderSearch;
import com.shop.ShoppingMall.api.dto.OrderDtoWithItem;
import com.shop.ShoppingMall.api.dto.SimpleOrderDto;
import com.shop.ShoppingMall.api.dto.SimpleOrderQueryDto;
import com.shop.ShoppingMall.domain.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
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

    @GetMapping("/api/v4/simple-orders")
    public List<SimpleOrderQueryDto> ordersV4(){
        List<SimpleOrderQueryDto> result = orderRepository.findOrderDtos();

        return result;
    }

    @GetMapping("/api/v3.1/orders")
    public List<OrderDtoWithItem> ordersv3_1(){
        List<Order> orders = orderRepository.findAllWithMemberDelivery();

        List<OrderDtoWithItem> orderDtos = orders.stream()
                .map(order -> new OrderDtoWithItem(order))
                .collect(Collectors.toList());

        return orderDtos;
    }
}
