package com.expertsoft.core.model.entity;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.NaturalId;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

import static org.hibernate.annotations.CacheConcurrencyStrategy.READ_ONLY;

@Entity
@Immutable
@Cache(usage = READ_ONLY, region = "products")
public class MobilePhone {

    @Id
    private Long id;

    @NaturalId
    @Basic(optional = false)
    private String model;

    @Basic(optional = false)
    private String display;

    @Basic(optional = false)
    private String length;

    @Basic(optional = false)
    private String width;

    @Basic(optional = false)
    private String color;

    @Basic(optional = false)
    private Double price;

    @Basic(optional = false)
    private String camera;

    public MobilePhone() {}

    public MobilePhone(Long id, String model, Double price) {
        this.id = id;
        this.model = model;
        this.price = price;
    }

    public MobilePhone(Long id, String model, String display, String length, String width, String color, Double price, String camera) {
        this.id = id;
        this.model = model;
        this.display = display;
        this.length = length;
        this.width = width;
        this.color = color;
        this.price = price;
        this.camera = camera;
    }

    public Long getId() {
        return id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getCamera() {
        return camera;
    }

    public void setCamera(String camera) {
        this.camera = camera;
    }

    @Override
    public int hashCode() {
        return Objects.hash(model);
    }

    @Override
    public boolean equals(Object o) {
        if ((o == null) || !getClass().isAssignableFrom(o.getClass())) {
            return false;
        }
        var phone = (MobilePhone) o;
        return Objects.equals(model, phone.getModel());
    }

    @Override
    public String toString() {
        return "MobilePhone{" +
                "id=" + id +
                ", model='" + model + '\'' +
                ", display='" + display + '\'' +
                ", length='" + length + '\'' +
                ", width='" + width + '\'' +
                ", color='" + color + '\'' +
                ", price=" + price +
                ", camera='" + camera + '\'' +
                '}';
    }
}
