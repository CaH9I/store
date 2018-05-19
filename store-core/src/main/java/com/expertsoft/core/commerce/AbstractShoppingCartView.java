package com.expertsoft.core.commerce;

import com.expertsoft.core.model.entity.MobilePhone;

import java.util.Map;

public abstract class AbstractShoppingCartView implements ShoppingCartView {

    protected Map<MobilePhone, Integer> items;
    protected int numberOfItems;
    protected double subtotal;

    @Override
    public Map<MobilePhone, Integer> getItems() {
        return items;
    }

    @Override
    public int getNumberOfItems() {
        return numberOfItems;
    }

    @Override
    public double getSubtotal() {
        return subtotal;
    }
}
