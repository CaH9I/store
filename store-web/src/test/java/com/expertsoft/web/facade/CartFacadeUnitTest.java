package com.expertsoft.web.facade;

import com.expertsoft.web.dto.AddToCartResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;

import static java.util.Collections.singletonList;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CartFacadeUnitTest {

    private static final String MESSAGE = "test";

    @InjectMocks
    private CartFacade cartFacade;

    @Mock
    private MessageSourceAccessor messageSourceAccessor;

    @Mock
    private Errors errors;

    @Before
    public void setup() {
        when(errors.getAllErrors()).thenReturn(singletonList(new ObjectError("", MESSAGE)));
        when(messageSourceAccessor.getMessage(any(MessageSourceResolvable.class))).thenReturn(MESSAGE);
    }

    @Test
    public void addToCartError() {
        AddToCartResponse response = cartFacade.addToCartError(errors);

        assertEquals(AddToCartResponse.of(singletonList(MESSAGE)), response);
    }
}
