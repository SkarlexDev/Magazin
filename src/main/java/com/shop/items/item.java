package com.shop.items;


public class item{
	private String id;
	private String productName;
	private String description;	
	private double price;
	private String imageURL;
	private String itemlink;
	enum Category {
		Telefoane,
		Televizoare,
		laptopuri
		}
	private String categoryitem;
	
	public item(String productName, String description, double price, String imageURL, Category category) {
		super();
		this.productName = productName;
		this.description = description;
		this.price = price;
		this.imageURL = imageURL;
		this.itemlink = productName.replaceAll(" ", "_").replaceAll("-", "").replaceAll(",", "");
		this.id = itemlink;
		this.categoryitem = category.toString();
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

	public String getCategoryitem() {
		return categoryitem;
	}

	public void setCategoryitem(String categoryitem) {
		this.categoryitem = categoryitem;
	}
		

}
