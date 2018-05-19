package com.expertsoft.core.commerce;

import com.expertsoft.core.model.entity.MobilePhone;

import java.util.Map;

public interface ShoppingCartView {

    Map<MobilePhone, Integer> getItems();

    int getNumberOfItems();

    double getSubtotal();
}
