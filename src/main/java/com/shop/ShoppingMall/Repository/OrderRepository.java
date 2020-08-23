package com.shop.ShoppingMall.Repository;

import com.shop.ShoppingMall.domain.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

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

    public List<Order> findALL(OrderSearch orderSearch){
        //to-do 동적 쿼리(조건: 멤버 is Null, 주문상태 is Null 검사)
        return em.createQuery("select o from Order o join o.member m" +
                " where o.status = :status" +
                " and m.name like :name", Order.class)
                .setParameter("status", orderSearch.getOrderStatus())
                .setParameter("name", orderSearch.getMemberName())
                .setMaxResults(1000)
                .getResultList();
    }
}
