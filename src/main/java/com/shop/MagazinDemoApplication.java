package com.shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.shop.items.Item;

@SpringBootApplication
public class MagazinDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MagazinDemoApplication.class, args);
		Item i = new Item("Televizor QLED Smart SAMSUNG 65Q90T, Ultra HD 4K, HDR, 163 cm", "BlaBla", 1500, "temp");
		System.out.println(i);
		
	}

}
