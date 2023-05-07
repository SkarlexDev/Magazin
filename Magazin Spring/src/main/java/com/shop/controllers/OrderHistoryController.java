package com.shop.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.shop.configuration.MyUserDetails;
import com.shop.services.OrderItemsService;
import com.shop.services.OrderService;
import com.shop.services.UserService;
import com.shop.struct.Order;
import com.shop.struct.Orderitems;
import com.shop.struct.Role;
import com.shop.struct.User;

@Controller
public class OrderHistoryController {

    private final UserService userService;

    private final OrderItemsService orderitemsService;

    private final OrderService orderService;

    public OrderHistoryController(UserService userService, OrderItemsService orderitemsService,
            OrderService orderService) {
        this.userService = userService;
        this.orderitemsService = orderitemsService;
        this.orderService = orderService;
    }

    @GetMapping("/orderDetail/{orderId}")
    public String OderHistory(Model model, @PathVariable String orderId,
            @AuthenticationPrincipal MyUserDetails userDetails) throws InterruptedException {
        try {

            Optional<User> oUser = userService.getUserEmail(userDetails.getUsername());
            if (oUser.isPresent()) {
                User user = oUser.get();
                Order order = orderService.getOrderByOrderID(orderId);
                if (order.getUser().getId() != user.getId()) {
                    for (Role staff : user.getRoles()) {
                        if (staff.getName().contains("ADMIN")) {
                            List<Orderitems> orders = orderitemsService.getById(order.getId());
                            model.addAttribute("order", order);
                            model.addAttribute("orders", orders);
                            model.addAttribute("ordId", orderId);
                            return "OrderDetails";
                        }
                    }
                    return "redirect:/profile";
                }
                List<Orderitems> orders = orderitemsService.getById(order.getId());
                model.addAttribute("order", order);
                model.addAttribute("orders", orders);
                model.addAttribute("ordId", orderId);
            }
            return "OrderDetails";
        } catch (NullPointerException e) {
            System.out.println("File not exist");
            return "redirect:/error";

        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/error";
        }
    }
}
