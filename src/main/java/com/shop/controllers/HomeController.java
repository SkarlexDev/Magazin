package com.shop.controllers;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.shop.services.CartService;
import com.shop.services.ProductService;
import com.shop.struct.Product;
@Controller
public class HomeController {
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private CartService cartService;
			
	@GetMapping({"/home","/"})
	public String getAllproducts(Model model) {		
		List<Product> products = productService.findAll();		
		model.addAttribute("products", products);
		model.addAttribute("pageTitle", "Home");
		// cart info
		List<Product> cart_products = cartService.getAllproducts();
		model.addAttribute("cart_products", cart_products);
		model.addAttribute("totalprice", cartService.totalPrice);
		model.addAttribute("checkMap", cartService.productCart);
		model.addAttribute("removeid", "da");
		return "index";
	}
	
	@GetMapping("/shop/{category}")
	public String getCategory(@PathVariable String category, Model model) {
		List<Product> products = productService.findAllByCategory(category);
		model.addAttribute("products", products);	
		model.addAttribute("category",category);
		model.addAttribute("pageTitle", category);		
		return "index";	
	}


	@GetMapping("/shop/{category}/{productlink}")
	public String productPage(@PathVariable String category,@PathVariable String productlink, Model model) {
		Product product = productService.findByproductlink(productlink);
		model.addAttribute("productInfo", product);
		model.addAttribute("pageTitle", product.getProductName());
		// cart info poate
		return "item";
	}
	
	@GetMapping("/cart/add/{productlink}")
	public String addToCart(@PathVariable String productlink, Model model) {
		Product product = productService.findByproductlink(productlink);
		cartService.addProduct(product);
		return "redirect:/home";
	}
	
	@GetMapping("/cart/remove/{id}")
	public String removeFromCart(@PathVariable int id, Model model) {
		cartService.delete(id);
		return "redirect:/home";
	}
	
	@GetMapping("/cart/checkout")
	public String checkout(Model model) {
		cartService.checkout();
		return "redirect:/home";
	}




}