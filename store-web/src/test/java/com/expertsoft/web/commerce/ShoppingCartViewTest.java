package com.expertsoft.web.commerce;

import com.expertsoft.core.commerce.ShoppingCart;
import com.expertsoft.core.commerce.ShoppingCartView;
import com.expertsoft.core.model.entity.MobilePhone;
import com.expertsoft.web.test.WebApplicationTest;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static com.expertsoft.core.test.TestObjectFactory.getTestMobilePhone;
import static org.hamcrest.Matchers.hasEntry;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class ShoppingCartViewTest extends WebApplicationTest {

    private static final MobilePhone PHONE = getTestMobilePhone();
    private static final int QTY = 2;

    @Autowired
    private ShoppingCartView cartView;

    @Autowired
    private ShoppingCart cart;

    @Before
    public void init() {
        cart.add(PHONE.getId(), QTY);
    }

    @Test
    public void getItems() {
        assertEquals(1, cartView.getItems().size());
        assertThat(cartView.getItems(), hasEntry(PHONE, QTY));
    }

    @Test
    public void getNumberOfItems() {
        assertEquals(QTY, cartView.getNumberOfItems());
    }

    @Test
    public void getSubtotal() {
        assertEquals(PHONE.getPrice() * QTY, cartView.getSubtotal(), 0);
    }
}
