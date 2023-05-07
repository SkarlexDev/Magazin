package com.shop.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.shop.configuration.MyUserDetails;
import com.shop.services.OrderService;
import com.shop.services.UserService;
import com.shop.struct.Order;
import com.shop.struct.User;

@Controller
public class UserController {

    private final UserService userService;

    private final OrderService orderService;

    public UserController(UserService userService, OrderService orderService) {
        this.userService = userService;
        this.orderService = orderService;
    }

    @RequestMapping("/profile")
    public String showProfile(Model model, @AuthenticationPrincipal MyUserDetails userDetails) {
        Optional<User> user = userService.getUserEmail(userDetails.getUsername());
        if (user.isPresent()) {
            List<Order> orders = orderService.getOrdersByUser(user.get());
            model.addAttribute("userData", user.get());
            model.addAttribute("pageTitle", "Profile");
            model.addAttribute("orders", orders);
        }
        return "profile";
    }

    @PostMapping("/profile")
    public String editUser(@ModelAttribute User userData, @AuthenticationPrincipal MyUserDetails userDetails,
            Model model, RedirectAttributes redirectAttributes) {
        Optional<User> oUser = userService.getUserEmail(userDetails.getUsername());
        if (oUser.isPresent()) {
            User user = oUser.get();
            user.setFullName(userData.getFullName());
            user.setPhone(userData.getPhone());
            user.setAddress(userData.getAddress());
            user.setCity(userData.getCity());
            user.setState(userData.getState());
            user.setZip(userData.getZip());
            userService.updateUser(user);
            redirectAttributes.addFlashAttribute("method", "updated");
        }
        return "redirect:profile";
    }
}
