package com.shop.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.shop.struct.Item;

@Service
public class ItemService {

	Item item1 = new Item(
			"Televizor QLED Smart SAMSUNG 65Q90T, Ultra HD 4K, HDR, 163 cm", 
			"Descriere lunga", 
			1500.51,
			"https://lcdn.altex.ro/resize/media/catalog/product//Q/9/16fa6a9aef7ffd6209d5fd9338ffa0b1/Q90T_QTC1__011_Front3_Black_3c4ccb88.jpg",
			"Televizoare");

	Item item2 = new Item(
			"Telefon 1451", 
			"Text lung", 
			1550,
			"url",
			"Telefoane");
	Item item3 = new Item(
			"Laptop 777", 
			"Text lung", 
			4150,
			"url",
			"laptopuri");
	Item item4 = new Item(
			"Televizor 952", 
			"Text lung", 
			150,
			"url",
			"Televizoare");
	Item item5 = new Item(
			"Telefon 48481", 
			"Text lung",
			8850,
			"url",
			"Telefoane");
	private List<Item> items = new ArrayList<Item>();	
	//private List<Item> items = Arrays.asList(item1, item2,item3,item4,item5);

	
	public List<Item> getAllItems() {
		return items;	
	}

	public Item getItem(String id) {
		Item item = items.stream()
				.filter(t -> id.equals(t.getId()))
				.findFirst()
				.orElse(null);

		return item;
	}


	public List<Item> getAllItemsByCategory(String category) {

		List<String> avaibleCategory = new ArrayList<String>();
		for(Item i: items) {
			avaibleCategory.add(i.getCategory());
		}

		List<Item> it = new ArrayList<Item>();
		if(avaibleCategory.contains(category)) {
			for(Item i : items) {
				if(i.getCategory().contains(category)) {
					it.add(i);
				}
			}
		}
		return it;
	}
	public void setItem(Item item) {
		items.add(item);
	}
	
	public void edit(String id, Item it) {
		Item item = items.stream()
				.filter(t -> id.equals(t.getId()))
				.findFirst()
				.orElse(null);
		item.setProductName(it.getProductName());
		item.setDescription(it.getDescription());
		item.setImageURL(it.getImageURL());
		item.setPrice(it.getPrice());
		item.setCategory(it.getCategory());
		item.setId(it.getProductName().replaceAll(" ", "_").replaceAll("-", "").replaceAll(",", "").replaceAll("/", "."));
	}
	
	public void delete(String id) {
		List<Object> toRemove = new ArrayList<Object>();
		for(Item item : items) {
			if(item.getId().equals(id)) {				
				toRemove.add(item);
				System.out.println("Add to delete");
			}
		}
		items.removeAll(toRemove);
		System.out.println("Deleted");
			  
	}



}
