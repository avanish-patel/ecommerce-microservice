package com.solstice.shipmentservice.domain;

import java.time.LocalDate;
import java.util.Date;

public class ShipmentAggregation {

    private long orderId;
    private LocalDate shipemntDate;
    private LocalDate deliveryDate;
    private String productName;
    private int quantity;

    public ShipmentAggregation() {
    }

    public ShipmentAggregation(long orderId, LocalDate shipemntDate, LocalDate deliveryDate, String productName, int quantity) {
        this.orderId = orderId;
        this.shipemntDate = shipemntDate;
        this.deliveryDate = deliveryDate;
        this.productName = productName;
        this.quantity = quantity;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public LocalDate getShipemntDate() {
        return shipemntDate;
    }

    public void setShipemntDate(LocalDate shipemntDate) {
        this.shipemntDate = shipemntDate;
    }

    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(LocalDate deliveryDate) {
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
}
