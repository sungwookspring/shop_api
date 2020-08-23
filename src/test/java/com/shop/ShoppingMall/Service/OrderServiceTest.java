package com.shop.ShoppingMall.Service;

import com.shop.ShoppingMall.Execptions.NotEnoughStockException;
import com.shop.ShoppingMall.Repository.OrderRepository;
import com.shop.ShoppingMall.domain.Address;
import com.shop.ShoppingMall.domain.Member;
import com.shop.ShoppingMall.domain.Order;
import com.shop.ShoppingMall.domain.OrderStatus;
import com.shop.ShoppingMall.domain.item.Book;
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
        Member member1 = CreateMember();

        //책 등록
        Book book = CreateBook(10000, 10);

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

    @Test(expected = NotEnoughStockException.class)
    public void 상품주문_재고수량초과(){
        //given
        Member member1 = CreateMember();
        Book book = CreateBook(10000, 10);

        //when
        int orderCount = 11;
        Long orderId = orderService.order(member1.getId(), book.getId(), orderCount);

        //then
        fail("상품주문_재고수량초과 테스트 실패");
    }

    @Test
    public void 주문취소(){
        //given
        Member member1 = CreateMember();
        Book book = CreateBook(10000, 10);

        int orderCount = 7;
        Long orderId = orderService.order(member1.getId(), book.getId(), orderCount);

        //when
        orderService.cancelOrder(orderId);

        //then
        Order FindOrder = orderRepository.findOrderById(orderId);
        assertEquals("주문 상태는 cancel", OrderStatus.CANCLE, FindOrder.getStatus());
        assertEquals("주문이 취소되었으므로 상품 갯수는 10개", 10, book.getStockquantity());

    }

    private Book CreateBook(int price, int stockquantity) {
        Book book = new Book();
        book.setName("ABC");
        book.setPrice(price);
        book.setStockquantity(stockquantity);
        em.persist(book);

        return book;
    }

    private Member CreateMember() {
        Member member1 = new Member();
        member1.setName("Test1");
        member1.setAddress(new Address("서울", "강서구", "123456"));
        em.persist(member1);

        return member1;
    }
}