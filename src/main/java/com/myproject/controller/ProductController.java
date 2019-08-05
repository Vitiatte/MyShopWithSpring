package com.myproject.controller;

import com.myproject.entity.Product;
import com.myproject.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    @RequestMapping(value = "/user/products", method = RequestMethod.GET)
    public String getAllProductsForUser(Model model) {
        model.addAttribute("productsList", productService.getAllProducts());
        return "/user/products";
    }

    @RequestMapping(value = "/admin/products", method = RequestMethod.GET)
    public String getAllProductsForAdmin(Model model) {
        model.addAttribute("productsList", productService.getAllProducts());
        return "/admin/products";
    }

    @RequestMapping(value = "/admin/add_product", method = RequestMethod.GET)
    public String addProduct() {
        return "/admin/addProduct";
    }

    @RequestMapping(value = "/admin/add_product", method = RequestMethod.POST)
    public String addProduct(@RequestParam(value = "title", required = false) String title,
                             @RequestParam(value = "description", required = false) String description,
                             @RequestParam(value = "price", required = false) Double price,
                             Model model) {

        if (title == null || description == null || price == null || title.isEmpty()
                || description.isEmpty() || price < 0.0) {
            model.addAttribute("error", "Input data is incorrect. Please try again!");
            return "/admin/addProduct";
        } else {
            Product.Builder builder = Product.getBuilder()
                    .setTitle(title)
                    .setDescription(description)
                    .setPrice(price);
            productService.add(builder.build());
            model.addAttribute("productsList", productService.getAllProducts());
            return "/admin/products";
        }
    }

    @RequestMapping(value = "/admin/delete_product", method = RequestMethod.GET)
    public String deleteProduct(@RequestParam("id") long id) {
        productService.delete(productService.getProductById(id).get());
        return "redirect:/admin/products";
    }

    @RequestMapping(value = "/admin/update_product", method = RequestMethod.GET)
    public String updateProduct(@RequestParam("id") long id,
                                Model model) {
        Optional<Product> optionalProduct = productService.getProductById(id);

        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            model.addAttribute("product", product);
            return "/admin/updateProduct";
        } else {
            return "redirect:/admin/products";
        }
    }

    @RequestMapping(value = "/admin/update_product", method = RequestMethod.POST)
    public String updateProduct(@RequestParam("id") long id,
                                @RequestParam(value = "title") String title,
                                @RequestParam(value = "description") String description,
                                @RequestParam(value = "price") Double price,
                                Model model) {

        Product product = Product.getBuilder()
                .setId(id)
                .setTitle(title)
                .setDescription(description)
                .setPrice(price)
                .build();
        productService.update(product);
        return "redirect:/admin/products";
    }
}
