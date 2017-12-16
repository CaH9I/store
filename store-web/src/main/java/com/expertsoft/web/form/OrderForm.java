package com.expertsoft.web.form;

import org.hibernate.validator.constraints.NotBlank;

public class OrderForm {

    @NotBlank(message = "{form.emptyField}")
    private String firstName;

    @NotBlank(message = "{form.emptyField}")
    private String lastName;

    @NotBlank(message = "{form.emptyField}")
    private String address;

    @NotBlank(message = "{form.emptyField}")
    private String phoneNumber;

    private String additionalInfo;

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
}
