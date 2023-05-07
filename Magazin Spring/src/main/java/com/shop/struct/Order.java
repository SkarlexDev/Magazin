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

    @Column(name = "user_fullName")
    private String uFullName;

    @Column(name = "user_phone")
    private String uPhone;

    @Column(name = "user_zip")
    private String uZip;

    @Column(name = "user_city")
    private String uCity;

    @Column(name = "user_state")
    private String uState;

    @Column(name = "user_address")
    private String uAddress;

    @Column(name = "user_email")
    private String uEmail;

    public Order() {
    }

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

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getuFullName() {
        return uFullName;
    }

    public void setuFullName(String uFullName) {
        this.uFullName = uFullName;
    }

    public String getuPhone() {
        return uPhone;
    }

    public void setuPhone(String uPhone) {
        this.uPhone = uPhone;
    }

    public String getuZip() {
        return uZip;
    }

    public void setuZip(String uZip) {
        this.uZip = uZip;
    }

    public String getuCity() {
        return uCity;
    }

    public void setuCity(String uCity) {
        this.uCity = uCity;
    }

    public String getuState() {
        return uState;
    }

    public void setuState(String uState) {
        this.uState = uState;
    }

    public String getuAddress() {
        return uAddress;
    }

    public void setuAddress(String uAddress) {
        this.uAddress = uAddress;
    }

    public String getuEmail() {
        return uEmail;
    }

    public void setuEmail(String uEmail) {
        this.uEmail = uEmail;
    }

    public void saveOrder(String uFullName, String uZip, String uAddress, String uState, String uPhone,
            String uCity, String uEmail) {
        DecimalFormat myFormatter = new DecimalFormat("ORD000000");
        this.orderID = myFormatter.format(this.id);
        this.uFullName = uFullName;
        this.uZip = uZip;
        this.uAddress = uAddress;
        this.uState = uState;
        this.uPhone = uPhone;
        this.uCity = uCity;
        this.uEmail = uEmail;
    }

}
