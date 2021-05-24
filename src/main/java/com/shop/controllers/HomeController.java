package com.shop.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.shop.services.ItemService;
import com.shop.struct.Item;


@Controller
public class HomeController {
	
	@Autowired
	private ItemService itemService;
			
	@GetMapping({"/home","/"})
	public String getAllItems(Model model) {		
		List<Item> items = itemService.findAll();		
		model.addAttribute("items", items);
		model.addAttribute("pageTitle", "Home");
		return "index";
	}
	@GetMapping("/shop/{category}")
	public String getCategory(@PathVariable String category, Model model) {
		List<Item> items = itemService.findAllByCategory(category);
		model.addAttribute("items", items);	
		model.addAttribute("category",category);
		model.addAttribute("pageTitle", category);
		return "index";	
	}

}
