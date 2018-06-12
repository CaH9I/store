package com.expertsoft.web.dto;

import java.util.List;

import static java.util.Collections.emptyList;

public class AddToCartResponse {

    private Integer numberOfItems;
    private String subtotal;
    private List<String> errors = emptyList();

    public static AddToCartResponse of(Integer numberOfItems, String subtotal) {
        AddToCartResponse result = new AddToCartResponse();
        result.numberOfItems = numberOfItems;
        result.subtotal = subtotal;
        return result;
    }

    public static AddToCartResponse of(List<String> errors) {
        AddToCartResponse result = new AddToCartResponse();
        result.errors = errors;
        return result;
    }

    public Integer getNumberOfItems() {
        return numberOfItems;
    }

    public String getSubtotal() {
        return subtotal;
    }

    public List<String> getErrors() {
        return errors;
    }

    @Override
    public String toString() {
        return "AddToCartResponse{" +
                "numberOfItems=" + numberOfItems +
                ", subtotal=" + subtotal +
                ", errors=" + errors +
                '}';
    }
}
