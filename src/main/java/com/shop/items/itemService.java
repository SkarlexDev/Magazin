package com.shop.items;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.shop.items.item.Category;

@Service
public class itemService {

	item item1 = new item(
			"Televizor QLED Smart SAMSUNG 65Q90T, Ultra HD 4K, HDR, 163 cm", 
			"Descriere lunga", 
			1500.51,
			"https://lcdn.altex.ro/resize/media/catalog/product//Q/9/16fa6a9aef7ffd6209d5fd9338ffa0b1/Q90T_QTC1__011_Front3_Black_3c4ccb88.jpg",
			Category.Televizoare);

	item item2 = new item(
			"Telefon 1451", 
			"Text lung", 
			1550,
			"url",
			Category.Telefoane);
	item item3 = new item(
			"Laptop 777", 
			"Text lung", 
			4150,
			"url",
			Category.laptopuri);
	item item4 = new item(
			"Televizor 952", 
			"Text lung", 
			150,
			"url",
			Category.Televizoare);
	item item5 = new item(
			"Telefon 48481", 
			"Text lung",
			8850,
			"url",
			Category.Telefoane);
	private List<item> items = Arrays.asList(item1, item2,item3,item4,item5);	

	public List<item> getAllItems() {
		return items;	
	}

	public item getItem(String id) {
		item item = items.stream()
				.filter(t -> id.equals(t.getId()))
				.findFirst()
				.orElse(null);

		return item;
	}


	public List<item> getAllItemsByCategory(String category) {

		List<String> avaibleCategory = new ArrayList<String>();
		for(item i: items) {
			avaibleCategory.add(i.getCategoryitem());
		}

		List<item> it = new ArrayList<item>();
		if(avaibleCategory.contains(category)) {
			//System.out.println("da contine");
			//System.out.println(category);
			for(item i : items) {
				if(i.getCategoryitem().contains(category)) {
					it.add(i);
				}
			}
		}
		return it;
	}



}
