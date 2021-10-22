package com.shop.struct;

import java.text.DecimalFormat;
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

	@Column(name = "ORDERID")
	private String orderID;
	
	@Column(name = "user_full_name")
	private String uName;
	
	@Column(name = "user_phone")
	private String uPhone;
	
	@Column(name = "user_postalcode")
	private String uCodPostal;
	
	@Column(name = "user_Oras")
	private String uOras;
	
	@Column(name = "user_judet")
	private String uJudet;
	
	@Column(name = "user_adresa")
	private String uAdresa;
	
	@Column(name = "user_email")
	private String uEmail;
	
	@Column(name = "Issue")
	private boolean issue;	
	
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
		this.issue = false;
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

	public String getOrderID() {
		return orderID;
	}

	public String getuName() {
		return uName;
	}

	public String getuPhone() {
		return uPhone;
	}	

	public String getuCodPostal() {
		return uCodPostal;
	}

	public String getuOras() {
		return uOras;
	}

	public String getuJudet() {
		return uJudet;
	}

	public String getuAdresa() {
		return uAdresa;
	}

	public boolean isIssue() {
		return issue;
	}

	public void setIssue(boolean issue) {
		this.issue = issue;
	}	
	
	public String getuEmail() {
		return uEmail;
	}

	public void setuEmail(String uEmail) {
		this.uEmail = uEmail;
	}

	public void saveOrder(String uln, String ufn, String uCodPostal,
			String uAdresa, String uJudet,String uPhone, String uOras, String uEmail) {
		DecimalFormat myFormatter = new DecimalFormat("ORD000000");
		this.orderID = myFormatter.format(this.id);
		this.uName = uln.toUpperCase().replaceAll(" ", "").replaceAll("-", "") + "_" + ufn.toLowerCase().replaceAll(" ", "_").replaceAll("-", "") ;
		this.uCodPostal = uCodPostal;
		this.uAdresa = uAdresa;
		this.uJudet = uJudet;
		this.uPhone = uPhone;
		this.uOras = uOras;
		this.uEmail = uEmail;
	}
	
	

}
