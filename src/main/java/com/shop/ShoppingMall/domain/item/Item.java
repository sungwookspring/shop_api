package com.shop.ShoppingMall.domain.item;

import com.shop.ShoppingMall.Execptions.NotEnoughStockException;
import com.shop.ShoppingMall.domain.Category;
import com.shop.ShoppingMall.domain.OrderItem;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
public abstract class Item {

    @Id @GeneratedValue
    @Column(name = "item_id")
    private long id;

    private String name;

    private int price;

    private int stockquantity;

    @ManyToMany(mappedBy = "items")
    List<Category> categories = new ArrayList<>();

    /***
     * 재고를 증가
     * @param quantity 더할 재고
     */
    public void addstock(int quantity){
        this.stockquantity += quantity;
    }

    /***
     * 재고를 감소
     * @param quantity 뺄 재고
     */
    public void removestock(int quantity){
        //재고 검사
        //0보다 작은지 검사
        int reststock = this.stockquantity - quantity;
        if(reststock < 0){
            throw new NotEnoughStockException("재고빼기 오류");

        }
        this.stockquantity = reststock;
    }
}
