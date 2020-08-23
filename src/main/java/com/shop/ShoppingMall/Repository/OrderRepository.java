package com.shop.ShoppingMall.Repository;

import com.shop.ShoppingMall.domain.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class OrderRepository {

    private final EntityManager em;

    @Autowired
    public OrderRepository(EntityManager em) {
        this.em = em;
    }

    /***
     * 주문 생성
     */
    public void save(Order order){
        em.persist(order);
    }

    /***
     * 주문 조회(기준: id)
     * @param id
     * @return
     */
    public Order findOrderById(Long id){
        return em.find(Order.class, id);
    }
}
