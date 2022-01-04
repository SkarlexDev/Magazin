package com.shop.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

	@Autowired
	private UserService userService;

	@Autowired
	private OrderService orderService;

	@RequestMapping("/profile")
	public String showProfile(Model model, @AuthenticationPrincipal MyUserDetails userDetails) {
		model.addAttribute("userData", userService.getUserEmail(email(userDetails)));
		model.addAttribute("pageTitle", "Profile");
		model.addAttribute("orders", orders(userService.getUserEmail(email(userDetails))));
		model.addAttribute("totalordercount", userService.GET_TOTAL_ORDERS_BY_USER(userService.getUserEmail(email(userDetails)).getId()));
		return "profile";
	}

	@PostMapping("/profile")
	public String editUser(@ModelAttribute User userData, @AuthenticationPrincipal MyUserDetails userDetails,
			Model model, RedirectAttributes redirectAttributes) {
		User user = userService.getUserEmail(email(userDetails));
		user.setLastName(userData.getLastName());
		user.setFirstName(userData.getFirstName());
		user.setTelefon(userData.getTelefon());
		user.setAdresa(userData.getAdresa());
		user.setOras(userData.getOras());
		user.setJudet(userData.getJudet());
		user.setCodPostal(userData.getCodPostal());
		userService.updateUser(user);
		redirectAttributes.addFlashAttribute("method", "updated");
		return "redirect:/profile";
	}

	@RequestMapping("/login")
	public String loginForm(Model model) {
		model.addAttribute("pageTitle", "Login");
		return "login";
	}

	@RequestMapping("/register")
	public String postUser(Model model) {
		model.addAttribute("userInfo", new User());
		model.addAttribute("pageTitle", "Register");
		return "register";
	}

	@PostMapping("/register_done")
	public String addUser(@ModelAttribute User userInfo) {
		userService.addUser(userInfo);
		return "redirect:/home";
	}

	// Temp
	@RequestMapping("/users")
	public String niceList(Model model) {
		model.addAttribute("usersId", users());
		return "users";
	}

	//////////////////////////////////////

	public List<User> users() {
		return userService.getAllUsers();
	}

	public String email(MyUserDetails userDetails) {
		return userDetails.getUsername();
	}

	public List<Order> orders(User user) {
		return orderService.getOrdersbyUser(user);
	}
}
