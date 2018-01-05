package com.expertsoft.core.model.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

// TODO
@Entity
@Table(name = "store_order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_id_seq")
    @SequenceGenerator(name = "order_id_seq", sequenceName = "order_id_seq")
    private Long id;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL) // TODO lazy
    @JoinColumn(name = "order_id")
    private List<CommerceItem> commerceItems = new ArrayList<>();

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "phone")
    private String phoneNumber;

    @Column(name = "additional_info")
    private String additionalInfo;

    @Column(name = "delivery_amount")
    private Double delivery;

    private String address;
    private Double subtotal;
    private Double total;
    private String state;

    public Order() {}

    public Order(Long id, String firstName, String lastName, String address, String phoneNumber, String state, String additionalInfo, Double subtotal, Double delivery, Double total) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.state = state;
        this.additionalInfo = additionalInfo;
        this.subtotal = subtotal;
        this.delivery = delivery;
        this.total = total;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<CommerceItem> getCommerceItems() {
        return commerceItems;
    }

    public void setCommerceItems(List<CommerceItem> commerceItems) {
        this.commerceItems = commerceItems;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }

    public Double getDelivery() {
        return delivery;
    }

    public void setDelivery(Double delivery) {
        this.delivery = delivery;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Order order = (Order) o;
        return Objects.equals(id, order.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", additionalInfo='" + additionalInfo + '\'' +
                ", delivery=" + delivery +
                ", address='" + address + '\'' +
                ", subtotal=" + subtotal +
                ", total=" + total +
                ", state='" + state + '\'' +
                '}';
    }
}
