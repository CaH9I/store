package com.expertsoft.core.model.entity;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.OnDelete;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.expertsoft.core.model.entity.OrderState.SUBMITTED;
import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;
import static org.hibernate.annotations.CacheConcurrencyStrategy.NONSTRICT_READ_WRITE;
import static org.hibernate.annotations.OnDeleteAction.CASCADE;

@Entity
@Table(name = "store_order")
@Cache(usage = NONSTRICT_READ_WRITE, region = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "order", cascade = PERSIST)
    @OnDelete(action = CASCADE)
    @Cache(usage = NONSTRICT_READ_WRITE, region = "orders")
    private List<OrderItem> orderItems = new ArrayList<>();

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

    @ManyToOne(fetch = LAZY, optional = false)
    private Account account;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
        orderItem.setOrder(this);
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

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !getClass().isAssignableFrom(o.getClass())) return false;
        var order = (Order) o;
        return Objects.equals(firstName, order.getFirstName()) &&
                Objects.equals(lastName, order.getLastName()) &&
                Objects.equals(phoneNumber, order.getPhoneNumber()) &&
                Objects.equals(additionalInfo, order.getAdditionalInfo()) &&
                Objects.equals(delivery, order.getDelivery()) &&
                Objects.equals(address, order.getAddress()) &&
                Objects.equals(subtotal, order.getSubtotal()) &&
                Objects.equals(total, order.getTotal()) &&
                state == order.getState();
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
