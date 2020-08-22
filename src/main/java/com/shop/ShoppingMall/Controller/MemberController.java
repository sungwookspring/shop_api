package com.shop.ShoppingMall.Controller;
import com.shop.ShoppingMall.Service.MemberService;
import com.shop.ShoppingMall.domain.Address;
import com.shop.ShoppingMall.domain.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@Slf4j
public class MemberController {
//    private final MemberService memberService; <-- 에러발생
    private MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }


    @GetMapping("/members/register")
    public String CreateMemberForm(Model model){
        log.info("[*] 회원가입 페이지 로드");

        model.addAttribute("memberForm", new MemberForm());
        return "members/register";
    }

    @PostMapping("/members/register")
    public String CreateMember(@Valid MemberForm memberform, BindingResult result){
        log.info("회원가입 시작");

        if(result.hasErrors()){
            log.info("사용자 입력값이 잘못됨");
            return "members/register";
        }

        Address address = new Address(memberform.getCity(), memberform.getStreet(), memberform.getZipcode());
        Member member = new Member();
        
        member.setName(memberform.getName());
        member.setAddrss(address);
        // 회원가입
        memberService.CreateMember(member);
        log.info("회원가입 성공");

        return "redirect:/";
    }
}