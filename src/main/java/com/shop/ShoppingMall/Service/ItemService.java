package com.shop.ShoppingMall.Service;

import com.shop.ShoppingMall.Repository.ItemRepository;
import com.shop.ShoppingMall.domain.item.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class ItemService {
    private final ItemRepository itemRepository;

    @Autowired
    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    /***
     * 아이템 등록
     * @param item 아이탬 객체
     */
    @Transactional
    public void save(Item item){
        itemRepository.save(item);
    }

    /***
     * 아이템 한개 찾기(기준: Id)
     * @param id 찾을 아이템 번호
     * @return
     */
    public Item findItemById(Long id){
        return itemRepository.findItemById(id);
    }

    /***
     * 모든 아이템 조회
     * @return
     */
    public List<Item> findALL(){
        return itemRepository.findALL();
    }
}
