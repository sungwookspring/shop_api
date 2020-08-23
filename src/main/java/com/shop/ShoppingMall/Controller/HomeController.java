package com.shop.ShoppingMall.Controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class HomeController {

    /***
     * 고객 메인 페이지
     * @param model
     * @return
     */
    @GetMapping("/")
    public String home(Model model){
        log.info("[*] home controller");
        return "index.html";
    }

    /***
     * 관리자 메인 페이지
     * @return
     */
    @GetMapping("/admin")
    public String adminHome(){
        log.info("[*] admin home controller");
        return "/admin/index.html";
    }
}
