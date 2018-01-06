package com.expertsoft.core.model.entity;

import static javax.persistence.FetchType.LAZY;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "commerce_item")
public class CommerceItem implements Serializable {

    @Id
    @ManyToOne(fetch = LAZY)
    private Order order;

    @Id
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "product_id")
    private MobilePhone phone;

    private Double price;
    private Integer quantity;

    public CommerceItem() {}

    public CommerceItem(MobilePhone phone, Integer quantity, Double price) {
        this.phone = phone;
        this.quantity = quantity;
        this.price = price;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(final Order order) {
        this.order = order;
    }

    public MobilePhone getPhone() {
        return phone;
    }

    public void setPhone(MobilePhone phone) {
        this.phone = phone;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final CommerceItem ci = (CommerceItem) o;
        return Objects.equals(order, ci.order) &&
                Objects.equals(phone, ci.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(order, phone);
    }
}
