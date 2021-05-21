package com.shop.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
		model.addAttribute("pageTitle", "Admin");
		return "admin";
	}
		
	@RequestMapping("/admin/product/new")
	public String newtItem(Model model) {
		model.addAttribute("itemInfo", new Item());
		model.addAttribute("method", "new");
		model.addAttribute("pageTitle", "New item");
		return "product";
	}
	@PostMapping("/admin/product/new")
	public String postItem(@ModelAttribute Item itemInfo , Model model) {
		itemInfo.setId(itemInfo.getProductName().replaceAll(" ", "_").replaceAll("-", "").replaceAll(",", "").replaceAll("/", "."));
		itemService.setItem(itemInfo);
		return "redirect:/admin";
	}
	
	@GetMapping("admin/product/edit/{id}")
	public String editProduct(@PathVariable String id, Model model) {
		Item item =itemService.getItem(id);
		model.addAttribute("itemInfo", item);
		model.addAttribute("method", "edit");
		model.addAttribute("pageTitle", "Edit");
		return "product";
	}
	
	@PostMapping("admin/product/edit/{id}")
	public String postEditProduct(@PathVariable String id, Model model, @ModelAttribute("itemInfo") Item itemInfo) {
		itemService.edit(id, itemInfo);
		return "redirect:/admin";
	}
	
	@GetMapping("admin/product/delete/{id}")
	public String deleteProduct(@PathVariable String id){
		itemService.delete(id);
		return "redirect:/admin";
	}
	
}
