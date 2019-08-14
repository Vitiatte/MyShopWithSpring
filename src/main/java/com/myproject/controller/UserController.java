package com.myproject.controller;

import com.myproject.entity.Basket;
import com.myproject.entity.User;
import com.myproject.entity.enums.UserRole;
import com.myproject.service.BasketService;
import com.myproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;

@Controller
@SessionAttributes({"user", "basket"})
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private BasketService basketService;

    //to use in session
    @ModelAttribute("user")
    public User createUser() {
        return new User();
    }

    //to use in session
    @ModelAttribute("basket")
    public Basket createProduct() {
        return new Basket();
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout() {
        return "login";
    }

    @RequestMapping(value = "/admin/users", method = RequestMethod.GET)
    public String allUsers(Model model) {
        model.addAttribute("usersList", userService.getAllUsers());
        return "/admin/users";
    }

    @RequestMapping(value = "/admin/delete_user", method = RequestMethod.GET)
    public String deleteUser(@RequestParam("id") long id,
                             Model model) {
        User user = userService.getUserById(id).get();
        userService.delete(user);
        return "redirect:/admin/users";
    }

    @RequestMapping(value = "/admin/update_user", method = RequestMethod.GET)
    public String updateUser(@RequestParam("id") long id,
                             Model model) {
        Optional<User> userOptional = userService.getUserById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            model.addAttribute("login", user.getLogin());
            model.addAttribute("userRole", user.getUserRole());
            model.addAttribute("roles", UserRole.values());
            return "/admin/updateUser";
        } else {
            return "/admin/users";
        }
    }

    @RequestMapping(value = "/admin/update_user", method = RequestMethod.POST)
    public String updateUser(@RequestParam("id") long id,
                             @RequestParam("login") String login,
                             @RequestParam("password") String password,
                             @RequestParam("role") String role,
                             Model model) {

        User user = User.getBuilder()
                .setId(id)
                .setLogin(login)
                .setHashedPassword(new BCryptPasswordEncoder().encode(password))
                .setUserRole(role)
                .build();
        userService.update(user);
        return "redirect:/admin/users";
    }

    @RequestMapping(value = "/admin/registration", method = RequestMethod.GET)
    public String registerNewUser(Model model) {
        model.addAttribute("roles", UserRole.values());
        return "/admin/registration";
    }

    @RequestMapping(value = "/admin/registration", method = RequestMethod.POST)
    public String registerNewUser(@RequestParam("login") String login,
                                  @RequestParam("password") String password,
                                  @RequestParam("passwordRepeat") String passwordRepeat,
                                  @RequestParam("role") String role,
                                  Model model) {
        User.Builder builder = User.getBuilder();
        boolean isAllDataCorrect = true;

        //Check login
        if (Objects.nonNull(login)
                && !login.isEmpty()
                && !userService.getUserByLogin(login).isPresent()) {
            builder.setLogin(login);
            model.addAttribute("login", login);
        } else {
            isAllDataCorrect = false;
        }
        //Check pass
        if (Objects.nonNull(password)
                && !password.isEmpty()
                && password.equals(passwordRepeat)) {
            model.addAttribute("password", password);
        } else {
            isAllDataCorrect = false;
        }
        //Check role
        if (Objects.nonNull(role)) {
            builder.setUserRole(role);
        } else {
            isAllDataCorrect = false;
        }

        if (isAllDataCorrect) {
            userService.add(builder.build(), password);
            model.addAttribute("usersList", userService.getAllUsers());
            return "/admin/users";
        } else {
            model.addAttribute("roles", UserRole.values());
            model.addAttribute("error", "Input data is incorrect. Please try again!");
            return "/admin/registration";
        }
    }

    @GetMapping("/start_page")
    public String getStartPage(@AuthenticationPrincipal User user,
                               @ModelAttribute Basket basket,
                               Model model) {
        if (user.getUserRole().equals("ROLE_ADMIN")) {
            return "redirect:/admin/users";
        } else {
            Optional<Basket> optionalBasketService =
                    basketService.getBasketForUser(user.getId());
            if (optionalBasketService.isPresent()) {
                model.addAttribute("basket", basket);
            } else {
                basket.setUserId(user.getId());
                basket.setProductList(new ArrayList<>());
                basketService.add(basket);
                model.addAttribute("basket", basket);
            }
            return "redirect:/user/products";
        }
    }
}
