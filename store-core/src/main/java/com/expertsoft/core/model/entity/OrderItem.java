package com.expertsoft.core.model.entity;

import org.hibernate.annotations.Cache;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import java.io.Serializable;
import java.util.Objects;

import static javax.persistence.FetchType.LAZY;
import static org.hibernate.annotations.CacheConcurrencyStrategy.NONSTRICT_READ_WRITE;

@Entity
@Cache(usage = NONSTRICT_READ_WRITE, region = "orders")
public class OrderItem {

    @EmbeddedId
    private OrderItemId id = new OrderItemId();

    @ManyToOne(fetch = LAZY)
    @MapsId("orderId")
    private Order order;

    @ManyToOne(fetch = LAZY)
    @MapsId("phoneId")
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
        id.setOrderId(order.getId());
    }

    public MobilePhone getPhone() {
        return phone;
    }

    public void setPhone(MobilePhone phone) {
        this.phone = phone;
        id.setPhoneId(phone.getId());
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

@Embeddable
class OrderItemId implements Serializable {

    @Column
    private Long orderId;

    @Column
    private Long phoneId;

    void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    void setPhoneId(Long phoneId) {
        this.phoneId = phoneId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderItemId that = (OrderItemId) o;

        return Objects.equals(orderId, that.orderId) &&
                Objects.equals(phoneId, that.phoneId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, phoneId);
    }
}
