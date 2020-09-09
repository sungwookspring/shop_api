package com.shop.ShoppingMall;

import com.shop.ShoppingMall.domain.*;
import com.shop.ShoppingMall.domain.item.Book;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

/***
 * UserA
 *  JPABook1
 *  JPABook2
 * UserB
 *  SpringBook1
 *  SpringBook2
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class initDB {
    private final InitService initService;

    @PostConstruct
    public void init(){
        initService.dbInit();
        initService.dbInit2();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService{
        private final EntityManager em;

        public void dbInit(){
            Member member = CreateMember("user1", "1", "111", "11");
            em.persist(member);

            Book book1 = createBook("jpa book1", 1000, 100);
            em.persist(book1);

            Book book2 = createBook("jpa book2", 1000, 100);
            em.persist(book2);

            OrderItem orderItem1 = OrderItem.createOrderItem(book1, 10000, 1);
            OrderItem orderItem2 = OrderItem.createOrderItem(book2, 20000, 1);

            Delivery delivery = new Delivery();
            delivery.setAddress(member.getAddress());

            Order order = Order.createOrder(member, delivery, orderItem1, orderItem2);

            em.persist(order);
        }

        public void dbInit2(){
            Member member = CreateMember("user1", "1", "111", "11");
            em.persist(member);

            Book book1 = createBook("jpa book1", 1000, 100);
            em.persist(book1);

            Book book2 = createBook("jpa book2", 1000, 100);
            em.persist(book2);

            OrderItem orderItem1 = OrderItem.createOrderItem(book1, 10000, 1);
            OrderItem orderItem2 = OrderItem.createOrderItem(book2, 20000, 1);

            Delivery delivery = new Delivery();
            delivery.setAddress(member.getAddress());

            Order order = Order.createOrder(member, delivery, orderItem1, orderItem2);

            em.persist(order);
        }
    }

    private static Book createBook(String bookname, int price, int stock) {
        Book book1 = new Book();
        book1.setName(bookname);
        book1.setPrice(price);
        book1.setStockquantity(stock);
        return book1;
    }

    private static Member CreateMember(String username, String city, String street, String zipcode) {
        Member member = new Member();
        member.setName(username);
        member.setAddress(new Address(city, street, zipcode));
        return member;
    }
}
