package com.shop.controllers;

import java.math.BigDecimal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.shop.services.CartItemService;
import com.shop.services.ProductService;
import com.shop.services.UserService;
import com.shop.struct.CartItem;
import com.shop.struct.Product;
import com.shop.struct.User;

@Controller
public class HomeController {

	@Autowired
	private ProductService productService;

	@Autowired
	private CartItemService cartItemService;

	@Autowired
	private UserService userService;

	@GetMapping({ "/home", "/" })
	public String getAllproducts(Model model, @AuthenticationPrincipal Authentication authentication) {
		User user = user(authentication);

		List<Product> products = productService.getAll();
		List<CartItem> listCartItems = cartItemService.getCartItems(user);
		BigDecimal totalPrice = totalPrice(listCartItems);

		model.addAttribute("listCartItems", listCartItems);
		model.addAttribute("products", products);
		model.addAttribute("totalprice", totalPrice);
		model.addAttribute("pageTitle", "Home");

		return "index";
	}

	@GetMapping("/shop/{category}")
	public String getCategory(@PathVariable String category, Model model,
			@AuthenticationPrincipal Authentication authentication) {
		User user = user(authentication);

		List<Product> products = productService.getAllByCategory(category);
		List<CartItem> listCartItems = cartItemService.getCartItems(user);

		BigDecimal totalPrice = totalPrice(listCartItems);

		model.addAttribute("listCartItems", listCartItems);
		model.addAttribute("products", products);
		model.addAttribute("category", category);
		model.addAttribute("totalprice", totalPrice);
		model.addAttribute("pageTitle", category);

		return "index";
	}

	@GetMapping("/shop/{category}/{productlink}")
	public String productPage(@PathVariable String category, @PathVariable String productlink, Model model) {
		Product product = product(productlink);
		model.addAttribute("productInfo", product);
		model.addAttribute("pageTitle", product.getProductName());
		return "item";
	}

	@GetMapping("/cart/add/{productlink}")
	public String addToCart(@PathVariable String productlink, Model model,
			@AuthenticationPrincipal Authentication authentication) {
		User user = user(authentication);
		Product product = product(productlink);
		cartItemService.addProduct(product, user);
		return "redirect:/home";
	}

	@GetMapping("/cart/remove/{productID}")
	public String removeFromCart(@PathVariable long productID, Model model,
			@AuthenticationPrincipal Authentication authentication) {
		User user = user(authentication);
		cartItemService.removeProduct(user.getId(), productID);
		return "redirect:/home";
	}

	@GetMapping("/cart/checkout")
	public String checkout(Model model, @AuthenticationPrincipal Authentication authentication) {
		User user = user(authentication);
		cartItemService.checkout(user);
		return "redirect:/home";
	}

	// simplify
	public User user(Authentication authentication) {
		User user = userService.getLoggedUser(authentication);
		return user;
	}

	public Product product(String productlink) {
		Product product = productService.getByProductlink(productlink);
		return product;
	}

	public BigDecimal totalPrice(List<CartItem> listCartItems) {
		BigDecimal totalPrice = new BigDecimal("0");

		for (CartItem lci : listCartItems) {
			String price = String.valueOf(lci.getProduct().getPrice() * lci.getQuantity());
			totalPrice = totalPrice.add(new BigDecimal(price));
		}
		return totalPrice;
	}

}