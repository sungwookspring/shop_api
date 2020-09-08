package com.shop.ShoppingMall.api;

import com.shop.ShoppingMall.Service.MemberService;
import com.shop.ShoppingMall.domain.Member;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberAPI_Controller {
    private final MemberService memberService;

    @PostMapping("/api/v1/members")
    public CreateMemberResponse save(@RequestBody Member member){
        memberService.CreateMember(member);
        return new CreateMemberResponse(member.getId());
    }

    /***
     * 생성응답 Dto
     */
    @Data
    static class CreateMemberResponse {
        private Long id;

        public CreateMemberResponse(Long id){
            this.id = id;
        }

    }

}
