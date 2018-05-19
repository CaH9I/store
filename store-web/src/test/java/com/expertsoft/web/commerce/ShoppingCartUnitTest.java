package com.expertsoft.web.commerce;

import com.expertsoft.core.commerce.ShoppingCartView;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.hasEntry;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class ShoppingCartUnitTest {

    @InjectMocks
    private ShoppingCartImpl cart;

    @Mock
    private ShoppingCartView cartView;

    @Before
    public void init() {
        cart.add(1L, 1);
        cart.add(2L, 2);
    }

    @Test
    public void addNewItem() {
        cart.add(4L, 2);

        assertEquals(3, cart.getItems().size());
        assertThat(cart.getItems(), allOf(
                hasEntry(1L, 1),
                hasEntry(2L, 2),
                hasEntry(4L, 2)));
    }

    @Test
    public void addExistingItem() {
        cart.add(1L, 2);

        assertEquals(2, cart.getItems().size());
        assertThat(cart.getItems(), allOf(
                hasEntry(1L, 3),
                hasEntry(2L, 2)));
    }

    @Test
    public void remove() {
        cart.remove(2L);

        assertEquals(1, cart.getItems().size());
        assertThat(cart.getItems(), hasEntry(1L, 1));
    }

    @Test
    public void update() {
        Map<Long, Integer> newItems = new HashMap<>();
        newItems.put(1L, 4);
        newItems.put(2L, 5);
        newItems.put(3L, 6);

        cart.update(newItems);

        assertEquals(2, cart.getItems().size());
        assertThat(cart.getItems(), allOf(
                hasEntry(1L, 4),
                hasEntry(2L, 5)));
    }

    @Test
    public void clear() {
        cart.clear();

        assertThat(cart.getItems().entrySet(), empty());
    }

    @Test
    public void getView() {
        assertEquals(cartView, cart.getView());
    }
}
