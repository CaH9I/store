package com.expertsoft.core.model.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

// TODO
@Entity
@Table(name = "commerce_item")
public class CommerceItem {

    @Id
    @ManyToOne // TODO lazy
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

}
