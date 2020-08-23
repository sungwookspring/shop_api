package com.shop.ShoppingMall.Service;

import com.shop.ShoppingMall.Repository.OrderRepository;
import com.shop.ShoppingMall.domain.Address;
import com.shop.ShoppingMall.domain.Member;
import com.shop.ShoppingMall.domain.Order;
import com.shop.ShoppingMall.domain.OrderStatus;
import com.shop.ShoppingMall.domain.item.Book;
import com.shop.ShoppingMall.domain.item.Item;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class OrderServiceTest {
    @Autowired
    EntityManager em;
    @Autowired
    OrderService orderService;
    @Autowired
    OrderRepository orderRepository;

    @Test
    public void 샘플주문(){
        /***
         * given
         */
        //회원 등록
        Member member1 = new Member();
        member1.setName("Test1");
        member1.setAddress(new Address("서울", "강서구", "123456"));

        em.persist(member1);

        //책 등록
        Book book = new Book();
        book.setName("ABC");
        book.setPrice(10000);
        book.setStockquantity(10);

        em.persist(book);

        /***
         * when
         */
        //책 2권 주문
        int ordercount = 2;
        Long orderId = orderService.order(member1.getId(), book.getId(), ordercount);

        /***
         * then
         */
        Order findOrder = orderRepository.findOrderById(orderId);

        //상품 주문상태
        assertEquals("상품 주문시 상태는 ORDER", OrderStatus.ORDER, findOrder.getStatus());
        assertEquals("주문 상품 종류", 1, findOrder.getOrderitems().size());
        assertEquals("주문 상품 가격", 10000 * ordercount, findOrder.getTotalPrice());
        assertEquals("남은 책 수량", 8, book.getStockquantity());
    }

    @Test
    public void 주문취소(){

    }

    @Test
    public void 샘플주문_재고수량초과() throws Exception{

    }
}