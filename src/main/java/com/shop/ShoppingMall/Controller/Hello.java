package com.shop.ShoppingMall.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Hello {

    @GetMapping("index")
    public String Hello(Model model){
        return "index.html";
    }
}
