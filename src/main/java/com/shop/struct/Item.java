package com.shop.struct;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Item{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(unique=true)
	private String itemlink;
	private String productName;
	private String description;	
	private double price;
	private String imageURL;
	private String category;
	
	
	
	public Item() {
		super();
	}

	public Item(String productName, String description, double price, String imageURL, String category) {
		super();
		this.productName = productName;
		this.description = description;
		this.price = price;
		this.imageURL = imageURL;
		this.category = category;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getImageURL() {
		return imageURL;
	}
	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}

	public String getItemlink() {
		return itemlink;
	}

	public void setItemlink(String itemlink) {
		this.itemlink = itemlink;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

		

}
