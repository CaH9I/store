package com.expertsoft.core.model.entity;

import org.hibernate.annotations.OnDelete;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.expertsoft.core.model.entity.Order.OrderState.SUBMITTED;
import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.GenerationType.IDENTITY;
import static org.hibernate.annotations.OnDeleteAction.CASCADE;

@Entity
@Table(name = "store_order")
public class Order {

    public enum OrderState {
        SUBMITTED, DELIVERED
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "order", cascade = PERSIST)
    @OnDelete(action = CASCADE)
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

    @Enumerated(EnumType.STRING)
    @Basic(optional = false)
    private OrderState state = SUBMITTED;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<CommerceItem> getCommerceItems() {
        return commerceItems;
    }

    public void addCommerceItem(CommerceItem commerceItem) {
        commerceItems.add(commerceItem);
        commerceItem.setOrder(this);
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

    public OrderState getState() {
        return state;
    }

    public void setState(OrderState state) {
        this.state = state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(firstName, order.firstName) &&
                Objects.equals(lastName, order.lastName) &&
                Objects.equals(phoneNumber, order.phoneNumber) &&
                Objects.equals(additionalInfo, order.additionalInfo) &&
                Objects.equals(delivery, order.delivery) &&
                Objects.equals(address, order.address) &&
                Objects.equals(subtotal, order.subtotal) &&
                Objects.equals(total, order.total) &&
                state == order.state;
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, phoneNumber, additionalInfo, delivery, address, subtotal, total, state);
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
