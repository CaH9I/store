package com.expertsoft.core.model.entity;

import static javax.persistence.CascadeType.ALL;

import static com.expertsoft.core.model.entity.Order.OrderState.SUBMITTED;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

import com.expertsoft.core.model.entity.listener.OrderListener;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "store_order")
@EntityListeners(OrderListener.class)
public class Order {

    public enum OrderState {
        SUBMITTED, DELIVERED
    }

    @Id
    @GeneratedValue(generator = "order_id_seq")
    @GenericGenerator(
            name = "order_id_seq",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = @Parameter(name = "sequence_name", value = "store_order_id_seq")
    )
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

    @Enumerated(EnumType.STRING)
    @Basic(optional = false)
    private OrderState state = SUBMITTED;

    public Long getId() {
        return id;
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

    public OrderState getState() {
        return state;
    }

    public void setState(OrderState state) {
        this.state = state;
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
