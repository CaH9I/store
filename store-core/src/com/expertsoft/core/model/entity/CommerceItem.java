package com.expertsoft.core.model.entity;

public class CommerceItem {
	private long id;
	private MobilePhone phone;
	private double price;
	private int quantity;
	
	public CommerceItem() {}
	
	public CommerceItem(MobilePhone phone, int quantity, double price) {
		this.phone = phone;
		this.quantity = quantity;
		this.price = price;
	}
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public MobilePhone getPhone() {
		return phone;
	}
	
	public void setPhone(MobilePhone phone) {
		this.phone = phone;
	}
	
	public double getPrice() {
		return price;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
