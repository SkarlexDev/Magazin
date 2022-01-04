package com.shop.struct;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "orderitems")
public class Orderitems {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "productId", insertable = false, updatable = false)
	private int productId;

	@Column(name = "quantity")
	private int quantity;

	@Column(name = "price")
	private double price;

	@Column(name = "order_id", insertable = false, updatable = false)
	private int orderId;

	@ManyToOne
	@JoinColumn(name = "order_id")
	private Order order;

	@OneToOne
	@JoinColumn(name = "productId")
	private Product product;
	
	public Orderitems() {
		super();
	}

	public Orderitems(int productId, int quantity, double price, int orderId, Order order, Product product) {
		super();
		this.productId = productId;
		this.quantity = quantity;
		this.price = price;
		this.orderId = orderId;
		this.order = order;
		this.product = product;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@Override
	public String toString() {
		return "Orderitems [id=" + id + ", productId=" + productId + ", quantity=" + quantity + ", price=" + price
				+ ", orderId=" + orderId + ", order=" + order + ", product=" + product + "]";
	}

}