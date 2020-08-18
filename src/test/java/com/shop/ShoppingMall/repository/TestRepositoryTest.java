package com.shop.ShoppingMall.repository;

import com.shop.ShoppingMall.domain.TestMember;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestRepositoryTest {

    @Autowired
    TestRepository repository;

    @Test
    @Transactional
    public void 회원가입(){
        //given
        TestMember member1 = new TestMember();
        member1.setName("test1");

        //when
        Long joinID = repository.join(member1);

        //that
        Assertions.assertThat(member1.getId()).isEqualTo(joinID);
        TestMember findmember = repository.findID(joinID);
        Assertions.assertThat(findmember).isEqualTo(member1);

    }

}