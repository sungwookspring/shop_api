package com.shop.ShoppingMall.Service;

import com.shop.ShoppingMall.Repository.MemberRepository;
import com.shop.ShoppingMall.domain.Member;
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
public class MemberServiceTest {
    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    EntityManager em;

    @Test
    public void 회원가입(){
        //given
        Member member1 = new Member();
        member1.setName("test1");

        //when
        Long saveID = memberService.CreateMember(member1);

        em.flush();
        //then
//        Assertions.assertThat(member1.getId()).isEqualTo(saveID);
        assertEquals(member1, memberRepository.findMemberById(saveID));
    }

    @Test
    public void 중복회원_예외(){

    }
}