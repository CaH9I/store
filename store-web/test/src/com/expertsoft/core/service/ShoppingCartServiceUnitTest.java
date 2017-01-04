package com.expertsoft.core.service;

import static org.hamcrest.collection.IsMapContaining.hasEntry;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.expertsoft.core.service.component.ShoppingCart;
import com.expertsoft.core.util.AnyEmptyMap;

@RunWith(MockitoJUnitRunner.class)
public class ShoppingCartServiceUnitTest {

    @InjectMocks
    private ShoppingCartService cartService = new ShoppingCartService();

    @Mock
    private ShoppingCart shoppingCart;

    private Map<Long, Integer> items = new HashMap<>();
    {
        items.put(1L, 1);
        items.put(2L, 2);
        items.put(3L, 3);
    }

    @Test
    public void clearCart() {
        cartService.clearCart();
        verify(shoppingCart).setItems(argThat(new AnyEmptyMap<>()));
    }

    @Test
    public void removeFromCart() {
        when(shoppingCart.getItems()).thenReturn(items);
        cartService.removeFromCart(2L);

        assertEquals(2, items.size());
        assertThat(items, hasEntry(1L, 1));
        assertThat(items, hasEntry(3L, 3));
    }

    @Test
    public void addToCartNewitem() {
        when(shoppingCart.getItems()).thenReturn(items);
        cartService.addToCart(4L, 2);

        assertThat(items, hasEntry(4L, 2));
    }

    @Test
    public void addToCartExistingItem() {
        when(shoppingCart.getItems()).thenReturn(items);
        cartService.addToCart(1L, 2);

        assertThat(items, hasEntry(1L, 3));
    }

    @Test
    public void updateCart() {
        when(shoppingCart.getItems()).thenReturn(items);

        Map<Long, Integer> newItems = new HashMap<>();
        newItems.put(1L, 4);
        newItems.put(2L, 5);
        newItems.put(3L, 6);

        cartService.updateCart(newItems);

        assertEquals(3, items.size());
        assertThat(items, hasEntry(1L, 4));
        assertThat(items, hasEntry(2L, 5));
        assertThat(items, hasEntry(3L, 6));
    }

}
