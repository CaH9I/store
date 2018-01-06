package com.expertsoft.core.model.entity;

import static javax.persistence.CascadeType.ALL;

import static com.expertsoft.core.util.OrderStates.SUBMITTED;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.expertsoft.core.model.entity.listener.OrderListener;

@Entity
@Table(name = "store_order")
@EntityListeners(OrderListener.class)
public class Order {

    // TODO
    public enum OrderState {
        SUBMITTED, DELIVERED
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_id_seq")
    @SequenceGenerator(name="order_id_seq", sequenceName = "store_order_id_seq")
    private Long id;

    @OneToMany(mappedBy = "order", cascade = ALL)
    private List<CommerceItem> commerceItems = new ArrayList<>();

    @Basic(optional = false)
    private String firstName;

    @Basic(optional = false)
    private String lastName;

    @Basic(optional = false)
    private String phoneNumber;

    @Column(length = 2047)
    private String additionalInfo;

    @Basic(optional = false)
    private Double delivery;

    @Basic(optional = false)
    private String address;

    @Basic(optional = false)
    private Double subtotal;

    @Basic(optional = false)
    private Double total;

    @Basic(optional = false)
    private String state = SUBMITTED;

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
