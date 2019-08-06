package com.myproject.controller;

import com.myproject.entity.Basket;
import com.myproject.entity.Order;
import com.myproject.entity.Product;
import com.myproject.entity.User;
import com.myproject.entity.enums.OrderStatus;
import com.myproject.service.BasketService;
import com.myproject.service.MailService;
import com.myproject.service.OrderService;
import com.myproject.util.CheckCodeGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.List;

@Controller
@SessionAttributes({"order"})
@RequestMapping("/user")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private BasketService basketService;

    @Autowired
    private MailService mailService;

    //to use in session
    @ModelAttribute("order")
    public Order createOrder() {
        return new Order();
    }

    @RequestMapping(value = "/create_order", method = RequestMethod.GET)
    public String createOrder(@SessionAttribute("user") User user,
                              Model model) {
        Long userId = user.getId();
        List<Product> productList =
                basketService.getBasketForUser(userId).get().getProductList();
        if (productList.size() > 0) {
            return "/user/createOrder";
        } else {
            model.addAttribute("error", "Your basket is empty");
            return "/user/basket";
        }
    }

    @RequestMapping(value = "/create_order", method = RequestMethod.POST)
    public String createOrder(@RequestParam("email") String email,
                              @RequestParam("address") String address,
                              @SessionAttribute("basket") Basket basket,
                              @SessionAttribute("user") User user,
                              @ModelAttribute("order") Order order,
                              Model model) {
        if (!email.isEmpty() && !address.isEmpty()) {
            Long userId = user.getId();
            order = Order.getBuilder()
                    .setEmail(email)
                    .setAddress(address)
                    .setCheckCode(CheckCodeGenerator.generate())
                    .setUserId(userId)
                    .setStatus(OrderStatus.IN_PROCESS)
                    .setProductList(basket.getProductList())
                    .build();
            order.setId(orderService.add(order));
            basket.getProductList().clear();
            basketService.update(basket);
            model.addAttribute("order", order);
            return "redirect:/user/accept_order";
        } else {
            model.addAttribute("error", "Invalid input data");
            return "/user/createOrder";
        }
    }

    @RequestMapping(value = "/accept_order", method = RequestMethod.GET)
    public String acceptOrder(@SessionAttribute("order") Order order,
                              Model model) {
        mailService.sendCheckCode(order);
        model.addAttribute("order", order);
        return "/user/acceptOrder";
    }

    @RequestMapping(value = "/accept_order", method = RequestMethod.POST)
    public String acceptOrder(@SessionAttribute("order") Order order,
                              @RequestParam("codeFromUser") String code,
                              Model model) {
        String realCode = order.getCheckCode();

        if (code.equals(realCode)) {
            order.setStatus(OrderStatus.DONE);
            orderService.update(order);
            return "/user/orderSuccessfulDone";
        } else {
            model.addAttribute("error", "Code is invalid");
            model.addAttribute("order", order);
            return "/user/acceptOrder";
        }
    }
}
