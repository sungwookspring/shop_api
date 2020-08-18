package com.shop.shop;

import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MemberRepositoryTest {

    @Autowired
    MemberRepository memberrepository;

    @Test
    @Transactional
    public void 회원가입(){
        //given
        Member member1 = new Member();
        member1.setName("test");

        //when
        Long saveID = memberrepository.save(member1);
        Member findmember = memberrepository.find(saveID);

        //then
        Assertions.assertThat(findmember.getId()).isEqualTo(member1.getId());
    }
}