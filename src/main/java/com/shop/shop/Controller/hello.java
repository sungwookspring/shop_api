package com.shop.shop.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller
public class hello {

    public String Hello(Model model){
        return "hello";
    }
}
