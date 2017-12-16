package com.expertsoft.core.model.entity;

import java.util.Objects;

public class CommerceItem {
    private MobilePhone phone;
    private double price;
    private int quantity;

    public CommerceItem() {}

    public CommerceItem(MobilePhone phone, int quantity, double price) {
        this.phone = phone;
        this.quantity = quantity;
        this.price = price;
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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((phone == null) ? 0 : phone.hashCode());
        long temp = Double.doubleToLongBits(price);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        result = prime * result + quantity;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if ((obj != null) && (getClass() == obj.getClass())) {
            CommerceItem other = (CommerceItem) obj;
            return Objects.equals(other.getPhone(), getPhone())
                && Double.doubleToLongBits(price) == Double.doubleToLongBits(other.price)
                && quantity == other.quantity;
        } else {
            return false;
        }
    }

}
