package com.pepcus.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;
@Entity
@NoArgsConstructor
@ToString
@AllArgsConstructor
@Table(name="address")
public class Address {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "addressType")
	@Size(min=10,message="name should not be less then ten characters")
	private String addressType;
	
	@Column(name = "street1")
	private String street1;
	
	@Column(name = "street2",nullable = false)
	private String street2;
	
	@Column(name = "city")
	@Size(min=10,message="name should not be less then ten characters")
	private String city;
	
	@Column(name = "state")
	@Size(min=10,message="name should not be less then ten characters")
	private String state;
	
	@Column(name = "country")
	@Size(min=8,message="name should not be less then ten characters")
	private String country;

	@ManyToOne
	private Employee employee;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getStreet1() {
		return street1;
	}

	public void setStreet1(String street1) {
		this.street1 = street1;
	}

	public String getStreet2() {
		return street2;
	}

	public void setStreet2(String street2) {
		this.street2 = street2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getAddressType() {
		return addressType;
	}

	public void setAddressType(String addressType) {
		this.addressType = addressType;
	}
	
}
