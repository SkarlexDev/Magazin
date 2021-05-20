package com.shop.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shop.services.ItemService;
import com.shop.struct.Item;
import com.shop.struct.User;

@Controller
public class ProductController {
	
	@Autowired
	private ItemService itemService;
	Item item;
	
	
	@GetMapping("/admin")
	public String adminPanel(Model model) {
		List<Item> items = itemService.getAllItems();		
		model.addAttribute("items", items);		
		return "admin";
	}
		
	@RequestMapping("/admin/product/new")
	public String postItem(Model model) {
		model.addAttribute("itemInfo", new Item());
		return "product";
	}
	@PostMapping("/admin/product/newDone")
	public String addItem(@ModelAttribute Item itemInfo , Model model) {
		System.out.println(itemInfo);
		itemService.setItem(itemInfo);
		return "redirect:/admin";
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
