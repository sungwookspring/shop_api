package com.shop.ShoppingMall.Controller;

import com.shop.ShoppingMall.Service.ItemService;
import com.shop.ShoppingMall.domain.item.Book;
import com.shop.ShoppingMall.domain.item.Item;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@Slf4j
public class ItemController {
    private final ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    /***
     * 상품등록 페이지
     * @param model
     * @return
     */
    @GetMapping("/admin/items/new")
    public String createForm(Model model){
        model.addAttribute("itemForm", new BookForm());
        return "admin/items/createItemForm";
    }

    /***
     * 상품등록 메소드
     */
    @PostMapping("/admin/items/new")
    public String create(BookForm bookForm){
        log.info("[*] 상품등록 시작");
        Book book = new Book();
        book.setName(bookForm.getName());
        book.setPrice(bookForm.getPrice());
        book.setStockquantity(bookForm.getStockQuantity());
        book.setAuthor(bookForm.getAuthor());
        book.setIsbn(bookForm.getIsbn());

        log.info(Long.toString(book.getId()));

        // 상품 등록
        itemService.save(book);
        log.info("[*] 상품등록 완료");

        return "redirect:/admin";
    }

    /***
     * 관리자 입장 상품 리스트 조회
     * @param model
     * @return
     */
    @GetMapping("/admin/items/list")

    public String admin_list(Model model){
        log.info("[*] 관리자가 상품 조회");
        List<Item> items = itemService.findALL();
        model.addAttribute("items", items);
        return "admin/items/list";
    }

    /***
     * 회원입장에서 상품 리스트 조회
     * @param model
     * @return
     */
    @GetMapping("/items/list")
    public String list(Model model){
        log.info("[*] 고객이 상품 조회");
        List<Item> items = itemService.findALL();
        model.addAttribute("items", items);
        return "items/list";
    }

    /***
     * 상품 수정 요청(고객)
     * 상품 데이터를 상품 수정 페이지에 그대로 매핑
     * @param itemId
     * @param model
     * @return
     */
    @GetMapping("items/{itemId}/update")
    public String UpdateItemForm(@PathVariable("itemId")Long itemId, Model model){
        log.info("[*] 상품 수정 요청");
        //간단한 예제이므로 Book상품으로 캐스팅
        Book book = (Book) itemService.findItemById(itemId);

        //상품정보 매핑
        BookForm form = new BookForm();
        form.setId(book.getId());
        form.setName(book.getName());
        form.setPrice(book.getPrice());
        form.setStockQuantity(book.getPrice());
        form.setAuthor(book.getAuthor());
        form.setIsbn(book.getIsbn());

        model.addAttribute("form", form);

        return "items/update";
    }

    /***
     * 상품 수정(고객)
     * @param bookForm
     * @return 아이템 목록 페이지 이동
     */
    @PostMapping("items/{itemId}/update")
    public String UpdateItem(@ModelAttribute("form")BookForm bookForm){
        log.info("[*] 상품수정");

        Book book = new Book();
        book.setId(bookForm.getId());
        book.setName(bookForm.getName());
        book.setPrice(bookForm.getPrice());
        book.setStockquantity(bookForm.getStockQuantity());
        book.setAuthor(bookForm.getAuthor());
        book.setIsbn(bookForm.getIsbn());

        //상품 업데이트: 병합방법 구현
        itemService.save(book);

        return "redirect:/items/list";
    }

    /***
     * 상품 수정 요청(관리자)
     * 상품 데이터를 상품 수정 페이지에 그대로 매핑
     * @param itemId
     * @param model
     * @return
     */
    @GetMapping("admin/items/{itemId}/update")
    public String admin_UpdateItemForm(@PathVariable("itemId")Long itemId, Model model){
        log.info("[*] 상품 수정 요청");
        //간단한 예제이므로 Book상품으로 캐스팅
        Book book = (Book) itemService.findItemById(itemId);

        //상품정보 매핑
        BookForm form = new BookForm();
        form.setId(book.getId());
        form.setName(book.getName());
        form.setPrice(book.getPrice());
        form.setStockQuantity(book.getPrice());
        form.setAuthor(book.getAuthor());
        form.setIsbn(book.getIsbn());

        model.addAttribute("form", form);

        return "admin/items/update";
    }

    /***
     * 상품 수정(관리자)
     * @param bookForm
     * @return 아이템 목록 페이지 이동
     */
    @PostMapping("admin/items/{itemId}/update")
    public String admin_UpdateItem(@ModelAttribute("form")BookForm bookForm){
        log.info("[*] 상품수정");

        Book book = new Book();
        book.setId(bookForm.getId());
        book.setName(bookForm.getName());
        book.setPrice(bookForm.getPrice());
        book.setStockquantity(bookForm.getStockQuantity());
        book.setAuthor(bookForm.getAuthor());
        book.setIsbn(bookForm.getIsbn());

        //상품 업데이트: 병합방법 구현
        itemService.save(book);

        return "redirect:/admin/items/list";
    }
}
