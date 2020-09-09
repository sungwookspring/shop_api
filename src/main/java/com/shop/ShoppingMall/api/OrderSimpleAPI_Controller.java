package com.shop.ShoppingMall.api;

import com.shop.ShoppingMall.Repository.OrderRepository;
import com.shop.ShoppingMall.Repository.OrderSearch;
import com.shop.ShoppingMall.domain.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class OrderSimpleAPI_Controller {
    private final OrderRepository orderRepository;

    @GetMapping("/api/v1/simple-orders")
    public List<Order> ordersV1(){
        List<Order> all = orderRepository.findAllByString(new OrderSearch());
        return all;
    }
}
