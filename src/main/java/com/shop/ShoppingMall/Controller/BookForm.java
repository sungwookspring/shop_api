package com.shop.ShoppingMall.Controller;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class BookForm {
    private long id;

    //공통 속성
    private String name;
    private int price;
    private int stockQuantity;
    
    //책 속성
    private String autor;
    private String isbn;
}
