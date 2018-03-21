package com.expertsoft.core.model.entity;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;

import static javax.persistence.FetchType.LAZY;

@Entity
public class OrderItem implements Serializable {

    @Id
    @ManyToOne(fetch = LAZY)
    private Order order;

    @Id
    @ManyToOne(fetch = LAZY)
    private MobilePhone phone;

    @Basic(optional = false)
    private Double price;

    @Basic(optional = false)
    private Integer quantity;

    public OrderItem() {}

    public OrderItem(MobilePhone phone, Integer quantity, Double price) {
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
        final OrderItem ci = (OrderItem) o;
        return Objects.equals(order, ci.order) &&
                Objects.equals(phone, ci.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(order, phone);
    }
}
