package com.shop.ShoppingMall.repository;

import com.shop.ShoppingMall.domain.TestMember;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class TestRepository {

    @PersistenceContext
    EntityManager em;

    public long join(TestMember member){
        em.persist(member);

        return member.getId();
    }

    public TestMember findID(Long id){
        return em.find(TestMember.class, id);
    }
}
