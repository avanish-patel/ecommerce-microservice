package com.solstice.orderorderlineservice.domain;


import java.time.LocalDate;
import java.util.Date;

public class OrderLineItems {
    String productName;
    int quantity;
    private LocalDate shippingDate;
    private LocalDate deliveryDate;

    public OrderLineItems() {
    }

    public OrderLineItems(String productName, int quantity, LocalDate shippingDate, LocalDate deliveryDate) {
        this.productName = productName;
        this.quantity = quantity;
        this.shippingDate = shippingDate;
        this.deliveryDate = deliveryDate;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public LocalDate getShippingDate() {
        return shippingDate;
    }

    public void setShippingDate(LocalDate shippingDate) {
        this.shippingDate = shippingDate;
    }

    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(LocalDate deliveryDate) {
        this.deliveryDate = deliveryDate;
    }
}

