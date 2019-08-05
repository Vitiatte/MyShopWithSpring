package com.myproject.controller;

import com.myproject.entity.Basket;
import com.myproject.entity.Product;
import com.myproject.entity.User;
import com.myproject.service.BasketService;
import com.myproject.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.List;

@Controller
public class BasketController {

    @Autowired
    private BasketService basketService;

    @Autowired
    private ProductService productService;

    @RequestMapping(value = "/user/add_product_to_basket", method = RequestMethod.GET)
    public String addProductToBasket(@SessionAttribute("basket") Basket basket,
                                     @RequestParam("product_id") long productId) {
        Product product = productService.getProductById(productId).get();
        basket.getProductList().add(product);
        basketService.update(basket);

        return "redirect:/user/products";
    }

    @RequestMapping("/user/basket")
    public String basketForUser(@SessionAttribute("user") User user,
                                Model model) {
        Long userId = user.getId();
        List<Product> productList =
                basketService.getBasketForUser(userId).get().getProductList();
        model.addAttribute("products", productList);
        return "/user/basket";
    }
}
