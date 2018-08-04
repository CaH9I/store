package com.expertsoft.core.commerce;

import java.util.HashMap;
import java.util.Map;

import static java.util.Collections.unmodifiableMap;

public abstract class AbstractShoppingCart implements ShoppingCart {

    private final Map<Long, Integer> items = new HashMap<>();

    @Override
    public Map<Long, Integer> getItems() {
        return unmodifiableMap(items);
    }

    @Override
    public void add(long productId, int quantity) {
        var oldQuantity = items.putIfAbsent(productId, quantity);
        if (oldQuantity != null) {
            items.put(productId, oldQuantity + quantity);
        }
    }

    @Override
    public void remove(long productId) {
        items.remove(productId);
    }

    @Override
    public void update(Map<Long, Integer> items) {
        items.forEach(this.items::replace);
    }

    @Override
    public void clear() {
        items.clear();
    }
}
