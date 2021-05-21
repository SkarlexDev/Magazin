package com.shop.struct;


public class Item{
	private String id;
	private String productName;
	private String description;	
	private double price;
	private String imageURL;
	private String itemlink;
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
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
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

	@Override
	public String toString() {
		return "Item [id=" + id + ", productName=" + productName + ", description=" + description + ", price=" + price
				+ ", imageURL=" + imageURL + ", itemlink=" + itemlink + ", category=" + category + "]";
	}

		

}
