package com.shop.items;

public class Item {
	private String id;
	private String productName;
	private String description;	
	private double price;
	private String imageURL;
	private String Itemlink;
	
	public Item(String productName, String description, double price, String imageURL) {
		super();
		this.productName = productName;
		this.description = description;
		this.price = price;
		this.imageURL = imageURL;
		this.Itemlink = productName.replaceAll(" ", "_").replaceAll("-", "").replaceAll(",", "");
		this.id = Itemlink;
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
		return Itemlink;
	}
	public void setItemlink(String itemlink) {
		Itemlink = itemlink;
	}
	
	@Override
	public String toString() {
		
		return "ProductName= " + productName + "\nDescription= " + description + "\nPrice= " + price + "\nImageURL= " + imageURL + "\nItemLink= " + Itemlink;
	}

}
