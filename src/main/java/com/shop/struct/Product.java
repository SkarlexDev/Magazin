package com.shop.struct;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Products")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "productlink", unique = true)
	private String productlink;

	@Column(name = "productName")
	private String productName;

	@Column(name = "description", columnDefinition = "LONGTEXT")
	private String description;

	@Column(name = "price")
	private double price;

	@Column(name = "imageURL")
	private String imageURL;

	@Column(name = "category")
	private String category;

	@Column(name = "active")
	private boolean active;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProductlink() {
		return productlink;
	}

	public void setProductlink(String productlink) {
		this.productlink = productlink;
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

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", productlink=" + productlink + ", productName=" + productName + ", description="
				+ description + ", price=" + price + ", imageURL=" + imageURL + ", category=" + category + "]";
	}

}