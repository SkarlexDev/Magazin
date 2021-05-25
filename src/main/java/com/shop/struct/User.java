package com.shop.struct;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String lastName;
	private String firstName;
	private String telefon;
	private String adresa;
	private String oras;
	private String judet;
	private String codPostal;
	private String email;
	private String password;
	
	
	public User() {
		super();
	}
	public User(String lastName, String firstName, String telefon, String adresa, String oras, String judet,
			String codPostal, String email, String password) {
		super();
		this.lastName = lastName;
		this.firstName = firstName;
		this.telefon = telefon;
		this.adresa = adresa;
		this.oras = oras;
		this.judet = judet;
		this.codPostal = codPostal;
		this.email = email;
		this.password = password;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getTelefon() {
		return telefon;
	}
	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}
	public String getAdresa() {
		return adresa;
	}
	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}
	public String getOras() {
		return oras;
	}
	public void setOras(String oras) {
		this.oras = oras;
	}
	public String getJudet() {
		return judet;
	}
	public void setJudet(String judet) {
		this.judet = judet;
	}
	public String getCodPostal() {
		return codPostal;
	}
	public void setCodPostal(String codPostal) {
		this.codPostal = codPostal;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", lastName=" + lastName + ", firstName=" + firstName + ", telefon=" + telefon
				+ ", adresa=" + adresa + ", oras=" + oras + ", judet=" + judet + ", codPostal=" + codPostal + ", email="
				+ email + ", password=" + password + "]";
	}
	
	
	
}
