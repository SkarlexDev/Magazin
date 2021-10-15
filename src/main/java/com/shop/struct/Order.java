package com.shop.struct;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Orders")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "total_price")
	private double totalPrice;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "order_id")
	private List<Orderitems> orderItems;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id")
	private User user;

	public Order() {
	};

	public Order(User user) {
		this.user = user;
	}

	public Order(double totalPrice, List<Orderitems> orderItems, User user) {
		super();
		this.totalPrice = totalPrice;
		this.orderItems = orderItems;
		this.user = user;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public List<Orderitems> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<Orderitems> orderItems) {
		this.orderItems = orderItems;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
