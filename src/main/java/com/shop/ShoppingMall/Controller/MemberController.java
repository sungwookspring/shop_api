package com.shop.ShoppingMall.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class MemberController {
    //to-do memberserivce
    //    private MemberService;

    @GetMapping("/members/register")
    public String CreateMemberForm(Model model){
        model.addAttribute("memberForm", new MemberForm());
        return "members/register";
    }

    @PostMapping("/members/register")
    public String CreateMember(@Valid MemberForm memberform){
        //to-do 회원가입

        return "redirect:/";
    }
}