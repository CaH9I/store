package com.expertsoft.core.model.entity;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private long id;
    private List<CommerceItem> commerceItems = new ArrayList<>();
    private String firstName;
    private String lastName;
    private String address;
    private String phoneNumber;
    private String additionalInfo;
    private double delivery;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<CommerceItem> getCommerceItems() {
        return commerceItems;
    }

    public void setCommerceItems(List<CommerceItem> commerceItems) {
        this.commerceItems = commerceItems;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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

    public int getNumberOfItems() {
        return commerceItems.stream().mapToInt(ci -> ci.getQuantity()).sum();
    }

    public double getSubtotal() {
        return commerceItems.stream().mapToDouble(ci -> ci.getPrice() * ci.getQuantity()).sum();
    }

    public double getTotal() {
        return getSubtotal() + delivery;
    }
}
