package com.expertsoft.core.component;

public class FixedPriceDeliveryInfo implements DeliveryInfo {

    private double amount;

    public FixedPriceDeliveryInfo(double amount) {
        this.amount = amount;
    }

    @Override
    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

}
