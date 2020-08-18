package com.shop.ShoppingMall.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {

    @Id @GeneratedValue()
    @Column(name = "member_id")
    private Long id;

    private String name;

    @Embedded
    private Address addrss;

    @OneToMany(mappedBy = "order_id")
    private List<Order> orders = new ArrayList<>();

}
