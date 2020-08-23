package com.shop.ShoppingMall.Controller;

import com.shop.ShoppingMall.Service.ItemService;
import com.shop.ShoppingMall.domain.item.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
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
        model.addAttribute("form", new BookForm());
        return "admin/items/createItemForm";
    }

    /***
     * 상품등록 메소드
     */
    @PostMapping("/admin/items/new")
    public String create(BookForm bookForm){
        Book book = new Book();
        book.setName(bookForm.getName());
        book.setPrice(bookForm.getPrice());
        book.setStockquantity(bookForm.getStockQuantity());
        book.setAuthor(bookForm.getAuthor());
        book.setIsbn(bookForm.getIsbn());

        // 상품 등록
        itemService.save(book);

        return "redirect:/admin";
    }
}
