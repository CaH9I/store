package com.expertsoft.web.dto;

import java.util.List;
import java.util.Objects;

import static java.util.Collections.emptyList;

public class AddToCartResponse {

    private Integer numberOfItems;
    private String subtotal;
    private List<String> errors = emptyList();

    public static AddToCartResponse of(Integer numberOfItems, String subtotal) {
        var result = new AddToCartResponse();
        result.numberOfItems = numberOfItems;
        result.subtotal = subtotal;
        return result;
    }

    public static AddToCartResponse of(List<String> errors) {
        var result = new AddToCartResponse();
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        var response = (AddToCartResponse) o;
        return Objects.equals(numberOfItems, response.numberOfItems) &&
                Objects.equals(subtotal, response.subtotal) &&
                Objects.equals(errors, response.errors);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numberOfItems, subtotal, errors);
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
