package com.shop.controllers;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.shop.configuration.MyUserDetails;
import com.shop.services.OrderService;
import com.shop.services.OrderitemsService;
import com.shop.services.UserService;
import com.shop.struct.Order;
import com.shop.struct.Orderitems;
import com.shop.struct.User;

@Controller
public class OrderHistoryController {

	@Autowired
	private UserService userService;
	@Autowired
	private OrderitemsService orderitemsService;

	@Autowired
	private OrderService orderService;

	@GetMapping("/orderDetail/{userID}/{orderId}")
	public String OderHistory(Model model, @PathVariable Long userID, @PathVariable Long orderId,
			@AuthenticationPrincipal MyUserDetails userDetails) {
		try {
			String email = userDetails.getUsername();
			User user = userService.getUserEmail(email);
			Optional<Order> order = orderService.getOrderId(orderId);

			if (order.get().getUser().getId() != user.getId()) {
				System.out.println("Incorrect user");
				return "redirect:/profile";
			}
			List<Orderitems> orders = orderitemsService.getById(orderId);
			model.addAttribute("orders", orders);
			return "OrderDetails";
		} catch (Exception e) {
			return "redirect:/profile";
		}

	}
}
