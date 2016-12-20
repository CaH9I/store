package com.expertsoft.core.model.entity;

import java.util.ArrayList;
import java.util.List;

public class Order {
	private long id;
	private List<CommerceItem> items = new ArrayList<>();
	private String firstName;
	private String lastname;
	private String address;
	private String phone;
	private String additionalInfo;
	private double delivery;
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public List<CommerceItem> getCommerceItems() {
		return items;
	}
	
	public void setCommerceItems(List<CommerceItem> items) {
		this.items = items;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastname() {
		return lastname;
	}
	
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getPhone() {
		return phone;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getAdditionalInfo() {
		return additionalInfo;
	}
	
	public void setAdditionalInfo(String additionalInfo) {
		this.additionalInfo = additionalInfo;
	}
	
	public double getDelivery() {
		return delivery;
	}
	
	public void setDelivery(double delivery) {
		this.delivery = delivery;
	}

}
