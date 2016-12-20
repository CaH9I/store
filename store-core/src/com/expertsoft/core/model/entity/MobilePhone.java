package com.expertsoft.core.model.entity;

public class MobilePhone {
	private long id;
	private String model;
	private String display;
	private String length;
	private String width;
	private String color;
	private double price;
	private String camera;
	
	public MobilePhone() {}
	
	public MobilePhone(long id, String model, String display, String length, String width, String color, double price, String camera) {
		this.id = id;
		this.model = model;
		this.display = display;
		this.length = length;
		this.width = width;
		this.color = color;
		this.price = price;
		this.camera = camera;
	}

	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getModel() {
		return model;
	}
	
	public void setModel(String model) {
		this.model = model;
	}
	
	public String getDisplay() {
		return display;
	}
	
	public void setDisplay(String display) {
		this.display = display;
	}
	
	public String getLength() {
		return length;
	}
	
	public void setLength(String length) {
		this.length = length;
	}
	
	public String getWidth() {
		return width;
	}
	
	public void setWidth(String width) {
		this.width = width;
	}
	
	public String getColor() {
		return color;
	}
	
	public void setColor(String color) {
		this.color = color;
	}
	
	public double getPrice() {
		return price;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
	
	public String getCamera() {
		return camera;
	}
	
	public void setCamera(String camera) {
		this.camera = camera;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if ((obj == null) || (getClass() != obj.getClass())) {
			return false;
		}
		MobilePhone other = (MobilePhone) obj;
		return id == other.id ? true : false;
	}
	
}
