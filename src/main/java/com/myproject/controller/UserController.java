package com.myproject.controller;

import com.myproject.entity.Basket;
import com.myproject.entity.User;
import com.myproject.entity.enums.UserRole;
import com.myproject.service.BasketService;
import com.myproject.service.UserService;
import com.myproject.util.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.Optional;

@Controller
@SessionAttributes({"user", "basket"})
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private BasketService basketService;

    @ModelAttribute("user")
    public User setupUser() {
        return new User();
    }

    @ModelAttribute("basket")
    public Basket setupProduct() {
        return new Basket();
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String login() {
        return "/index";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestParam("login") String login,
                        @RequestParam("password") String pass,
                        @ModelAttribute("user") User user,
                        @ModelAttribute Basket basket,
                        Model model) {
        Optional<User> optionalUser = userService.getUserByLogin(login);
        User loggedUser;

        if (optionalUser.isPresent()) {
            loggedUser = optionalUser.get();
            if (loggedUser.getHashedPassword().equals(
                    PasswordUtil.encryptPassWithSalt(pass, loggedUser.getSalt()))) {
                basket = basketService.getBasketForUser(loggedUser.getId()).get();
                user = loggedUser;

                model.addAttribute("user", user);
                model.addAttribute("basket", basket);
                if (loggedUser.getUserRole().equals(UserRole.ADMIN)) {
                    return "redirect:/admin/users";
                } else {
                    return "redirect:/user/products";
                }
            } else {
                model.addAttribute("error", "Login or password is wrong");
                return "/index";
            }
        } else {
            model.addAttribute("error", "Login or password is wrong");
            return "";
        }
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout() {
        return "/index";
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
        String salt = userService.getUserById(id).get().getSalt();

        User user = User.getBuilder()
                .setId(id)
                .setLogin(login)
                .setHashedPassword(PasswordUtil.encryptPassWithSalt(password, salt))
                .setUserRole(UserRole.valueOf(role))
                .setSalt(salt)
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
            String salt = PasswordUtil.generateSalt();
            builder.setSalt(salt);
            builder.setHashedPassword(PasswordUtil.encryptPassWithSalt(password, salt));
            model.addAttribute("password", password);
        } else {
            isAllDataCorrect = false;
        }
        //Check role
        if (Objects.nonNull(role)) {
            builder.setUserRole(UserRole.valueOf(role));
        } else {
            isAllDataCorrect = false;
        }

        if (isAllDataCorrect) {
            userService.add(builder.build());
            model.addAttribute("usersList", userService.getAllUsers());
            return "/admin/users";
        } else {
            model.addAttribute("roles", UserRole.values());
            model.addAttribute("error", "Input data is incorrect. Please try again!");
            return "/admin/registration";
        }
    }
}
