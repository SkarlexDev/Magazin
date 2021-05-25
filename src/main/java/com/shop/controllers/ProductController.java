package com.shop.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


import com.shop.services.ItemService;
import com.shop.struct.Item;

@Controller
public class ProductController {
	
	@Autowired
	private ItemService itemService;
	Item item;
	
	
	@GetMapping("/admin")
	public String adminPanel(Model model) {
		List<Item> items = itemService.findAll();		
		model.addAttribute("items", items);
		model.addAttribute("pageTitle", "Admin");
		return "admin";
	}
		
	@GetMapping("/admin/product/new")
	public String newtItem(Model model) {
		model.addAttribute("itemInfo", new Item());
		model.addAttribute("method", "new");
		model.addAttribute("pageTitle", "New item");
		return "product";
	}
	@PostMapping("/admin/product/new")
	public String postItem(@ModelAttribute Item itemInfo , Model model) throws Exception {
		try {
			boolean allowpost = true;
			itemInfo.setItemlink(itemInfo.getProductName().replaceAll(" ", "_").replaceAll("-", "").replaceAll(",", "").replaceAll("/", "."));
			List<Item> items = new ArrayList<Item>();
			items.addAll(itemService.findAll());
			for(Item item:items) {
				if(item.getItemlink().equals(itemInfo.getItemlink())) {
					allowpost = false;
				}
			}
			System.out.println(allowpost);
			
			if(allowpost){
				itemService.addItem(itemInfo);
				return "redirect:/admin";
			}else {
				throw new Exception();
			}			
			
		} catch (Exception e) {
			model.addAttribute("itemInfo", new Item());
			model.addAttribute("method", "new");
			model.addAttribute("error","true");
			model.addAttribute("pageTitle", "New item");
			return "product";
		}
		
	}
	
	@GetMapping("admin/product/edit/{id}")
	public String editProduct(@PathVariable Long id, Model model) {
		Optional<Item> itemCall =itemService.findById(id);
		Item item = itemCall.get();
		System.out.println(item);
		model.addAttribute("itemInfo", item);
		model.addAttribute("method", "edit");
		model.addAttribute("pageTitle", "Edit");
		return "product";
	}
	
	@PostMapping("admin/product/edit/{id}")
	public String postEditProduct(@PathVariable Long id, Model model, @ModelAttribute("itemInfo") Item itemInfo) {
		try {
			itemInfo.setItemlink(itemInfo.getProductName().replaceAll(" ", "_").replaceAll("-", "").replaceAll(",", "").replaceAll("/", "."));
			itemService.editItem(itemInfo);
			return "redirect:/admin";			
		} catch (Exception e) {
			Optional<Item> itemCall =itemService.findById(id);
			Item item = itemCall.get();
			System.out.println(item);
			model.addAttribute("itemInfo", item);
			model.addAttribute("method", "edit");
			model.addAttribute("pageTitle", "Edit");
			model.addAttribute("error","true");
			return "product";
		}
	}
	
	@GetMapping("admin/product/delete/{id}")
	public String deleteItem(@PathVariable Long id){
		itemService.deleteItem(id);
		return "redirect:/admin";
	}
	
}
