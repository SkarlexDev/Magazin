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
		model.addAttribute("listCartItems", listCartItems(user(authentication)));
		model.addAttribute("products", products());
		model.addAttribute("totalprice", totalPrice(listCartItems(user(authentication))));
		model.addAttribute("pageTitle", "Home");
		return "index";
	}

	@GetMapping("/shop/{category}")
	public String getCategory(@PathVariable String category, Model model,
			@AuthenticationPrincipal Authentication authentication) {
		model.addAttribute("listCartItems", listCartItems(user(authentication)));
		model.addAttribute("products", products());
		model.addAttribute("category", category);
		model.addAttribute("totalprice", totalPrice(listCartItems(user(authentication))));
		model.addAttribute("pageTitle", category);
		return "index";
	}

	@GetMapping("/shop/{category}/{productlink}")
	public String productPage(@PathVariable String category, @PathVariable String productlink, Model model) {
		model.addAttribute("productInfo", productLink(productlink));
		model.addAttribute("pageTitle", productLink(productlink).getProductName());
		return "item";
	}

	@GetMapping("/cart/add/{productlink}")
	public String addToCart(@PathVariable String productlink, Model model,
			@AuthenticationPrincipal Authentication authentication) {
		cartItemService.addProduct(productLink(productlink), user(authentication));
		return "redirect:/home";
	}

	@GetMapping("/cart/remove/{productID}")
	public String removeFromCart(@PathVariable long productID, Model model,
			@AuthenticationPrincipal Authentication authentication) {
		cartItemService.removeProduct(user(authentication), productId(productID));
		return "redirect:/home";
	}

	@GetMapping("/cart/checkout")
	public String checkout(Model model, @AuthenticationPrincipal Authentication authentication) {
		cartItemService.checkout(user(authentication));
		return "redirect:/home";
	}

	//////////////////////////////////////

	public List<Product> products() {
		return productService.getAll();
	}

	public List<CartItem> listCartItems(User user) {
		return cartItemService.getCartItems(user);
	}

	public User user(Authentication authentication) {
		return userService.getLoggedUser(authentication);
	}

	public Product productId(long id) {
		return productService.getById(id).get();
	}

	public Product productLink(String productlink) {
		return productService.getByProductlink(productlink);
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