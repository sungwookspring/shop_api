package com.shop.ShoppingMall.api;

import com.shop.ShoppingMall.Service.MemberService;
import com.shop.ShoppingMall.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemberAPI_Controller {
    private final MemberService memberService;

    @PostMapping("/api/v1/members")
    public CreateMemberResponse save(@RequestBody @Valid Member member){
        memberService.CreateMember(member);
        return new CreateMemberResponse(member.getId());
    }

    @PostMapping("/api/v2/members")
    public CreateMemberResponse saveV2(@RequestBody @Valid CreateMemberRequest request){
        Member member = new Member();
        member.setName(request.getName());

        Long saveId = memberService.CreateMember(member);
        return new CreateMemberResponse(saveId);
    }

    @PutMapping("/api/v2/members/{id}")
    public UpdateMemberResponse updateMemberV2(@PathVariable("id") Long id, @RequestBody @Valid UpdateMemberRequset request){
        memberService.update(id, request.getName());
        Member updateMember = memberService.findMemberById(id);

        return new UpdateMemberResponse(updateMember.getId(), updateMember.getName());
    }

    @GetMapping("/api/v1/members")
    public List<Member> membersV1(){
        return memberService.findALL();
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

    @Data
    static class CreateMemberRequest {
        @NotEmpty
        private String name;
    }


    /***
     * 수정응답 Dto
     */
    @Data
    @AllArgsConstructor
    static class UpdateMemberResponse {
        private Long id;
        private String name;
    }

    @Data
    static class UpdateMemberRequset {
        private String name;
    }

}
