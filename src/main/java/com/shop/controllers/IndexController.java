package com.shop.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shop.items.Item;
import com.shop.items.ItemService;


@Controller
public class IndexController {
	
	@Autowired
	private ItemService itemService;
	
		
	@RequestMapping(value="/shop/{category}")
	public String getCategory(@PathVariable String category, Model model) {
		List<Item> items = itemService.getAllItemsByCategory(category);
		model.addAttribute("items", items);	
		model.addAttribute("category",category);
		return "index";	
	}
		
	@RequestMapping(value={"/home","/"})
	public String getAllItems(Model model) {		
		List<Item> items = itemService.getAllItems();		
		model.addAttribute("items", items);		
		return "index";
	}

}
