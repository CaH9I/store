package com.expertsoft.core.service.component;

import java.util.Map;

import com.expertsoft.core.model.entity.MobilePhone;

public class ShoppingCartView {

    private Map<MobilePhone, Integer> items;
    private int numberOfItems;
    private double subtotal;

    public ShoppingCartView() {}

    public ShoppingCartView(Map<MobilePhone, Integer> items, int numberOfItems, double subtotal) {
        this.items = items;
        this.numberOfItems = numberOfItems;
        this.subtotal = subtotal;
    }

    public Map<MobilePhone, Integer> getItems() {
        return items;
    }

    public void setItems(Map<MobilePhone, Integer> items) {
        this.items = items;
    }

    public int getNumberOfItems() {
        return numberOfItems;
    }

    public void setNumberOfItems(int numberOfItems) {
        this.numberOfItems = numberOfItems;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }
}
