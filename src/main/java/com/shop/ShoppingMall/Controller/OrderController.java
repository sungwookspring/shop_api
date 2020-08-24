package com.shop.ShoppingMall.Controller;

import com.shop.ShoppingMall.Repository.OrderSearch;
import com.shop.ShoppingMall.Service.ItemService;
import com.shop.ShoppingMall.Service.MemberService;
import com.shop.ShoppingMall.Service.OrderService;
import com.shop.ShoppingMall.domain.Member;
import com.shop.ShoppingMall.domain.Order;
import com.shop.ShoppingMall.domain.item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class OrderController {
    private final ItemService itemService;
    private final OrderService orderService;
    private final MemberService memberService;

    /***
     * 상품주문
     * @param model
     * @return
     */
    @GetMapping("/order")
    public String createForm(Model model){
        List<Member> members = memberService.findALL();
        List<Item> items = itemService.findALL();

        model.addAttribute("members",members);
        model.addAttribute("items",items);

        return "Order/order";
    }

    /***
     * 상품주문
     * @param memberId 회원Id
     * @param itemId 상품Id
     * @param count 상품수량
     * @return
     */
    @PostMapping("/order")
    public String order(@RequestParam("memberId")Long memberId,
                        @RequestParam("itemId")Long itemId,
                        @RequestParam("count")int count){

        orderService.order(memberId, itemId, count);
        return "redirect:/";
    }

    @GetMapping("/order/list")
    public String orderList(@ModelAttribute("orderSearch") OrderSearch orderSearch, Model model){
        List<Order> orders = orderService.findOrders(orderSearch);
        model.addAttribute("orders", orders);

        return "order/orderList";
    }
}
