package com.expertsoft.core.commerce;

import java.util.Map;

public interface ShoppingCart {

    void add(long productId, int quantity);

    void remove(long productId);

    void update(Map<Long, Integer> items);

    void clear();

    Map<Long, Integer> getItems();

    ShoppingCartView getView();
}
