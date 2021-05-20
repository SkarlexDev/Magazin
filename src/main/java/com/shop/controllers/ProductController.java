package com.shop.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.shop.services.ItemService;
import com.shop.struct.Item;

@Controller
public class ProductController {
	
	@Autowired
	private ItemService itemService;
	@GetMapping("/admin")
	public String adminPanel(Model model) {
		List<Item> items = itemService.getAllItems();		
		model.addAttribute("items", items);		
		return "admin";
	}
	
	@GetMapping("/admin/product")
	public String product() {
		return "product";
	}
	
	@GetMapping("/admin/product/new")
	public String productNew(Model model) {
		return "product";
	}
	@PostMapping("/admin/product/new")
	public String productNew() {
		return "product";
	}
	
	@GetMapping("admin/product/edit/1")
	public String editProduct(Model model) {
		return "product";
	}
	
	@PostMapping("admin/product/edit/{id}")
	public String editProduct() {
		return "product";
	}
	
	@PostMapping("/product/delete/{id}")
	public String deleteProduct(){
		return "redirect:/home";
	}
	
}
