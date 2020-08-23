package com.shop.ShoppingMall.Repository;

import com.shop.ShoppingMall.domain.item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class itemRepository {
    private final EntityManager em;

    /***
     * 생성자 주입
     * @param em
     */
    public itemRepository(EntityManager em) {
        this.em = em;
    }

    /***
     * item 저장
     * @param item item 객체
     */
    public void save(Item item){

        try{
            Long item_id = item.getId();
            em.merge(item);
        }catch(NullPointerException){
            //처음 아이템 등록
        }
    }

    /***
     * 아이템 찾기(기준: ID)
     * @param id  찾을 아이템 ID
     */
    public Item findItemById(Long id){
        return em.find(Item.class, id);
    }

    /***
     * 모든 아이템 조회
     * @return 모든 아이템의 리스트
     */
    public List<Item> findALL(){
        return em.createQuery("select i from Item as i", Item.class).getResultList();
    }
}
