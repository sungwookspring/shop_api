package com.shop.ShoppingMall.Repository;

import com.shop.ShoppingMall.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepository {
    private final EntityManager em;

    public void save(Member member){
        em.persist(member);
    }

    /***
     * 가입된 회원검색(기준: id)
     * @param id 찾은을 멤버의 id
     * @return 찾은 멤버의 객체(Member)
     */
    public Member findMemberById(Long id){
        return em.find(Member.class, id);
    }

    /***
     * 가입된 회원 검색(기준: 이름)
     * @param name 찾을 멤버의 이름
     * @return 찾은 멤버의 리스트
     */
    public List<Member> findMemberByName(String name){
        return em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
    }

    /***
     * 가입된 모든 회원 검색
     * @return 찾은 모든 멤버의 리스트
     */
    public List<Member> findALL(){
        return em.createQuery("select m from Member m", Member.class).getResultList();
    }
}
