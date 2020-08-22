package com.shop.ShoppingMall.Service;

import com.shop.ShoppingMall.Repository.MemberRepository;
import com.shop.ShoppingMall.domain.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class MemberService {

    @Autowired
    MemberRepository memberRepository;

    /***
     * 회원가입
     * @param member 회원가입할 멤버객체(html에서 입력한 값)
     * @return 가입완료된 멤버ID
     */
    @Transactional
    public Long CreateMember(Member member){
        //중복회원 가입 검사
        ValidateDuplicateMember(member);

        memberRepository.save(member);
        return member.getId();
    }

    /***
     * 회원가입 중복 검사
     * @param member 회원가입할 멤버객체
     */
    private void ValidateDuplicateMember(Member member) {

        List<Member> findMembers = memberRepository.findMemberByName(member.getName());
        if(!findMembers.isEmpty()){
            throw new IllegalStateException("이미 존재하는 회원입니다");
        }
    }

    //회원 전체 조인
    public List<Member> findALL(){
        return memberRepository.findALL();
    }

    //회원 조인(기준: id)
    public Member findMemberById(Long id){
        return memberRepository.findMemberById(id);
    }
}
