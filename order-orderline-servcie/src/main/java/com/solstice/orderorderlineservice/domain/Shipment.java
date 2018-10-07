package com.solstice.orderorderlineservice.domain;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.Date;

public class Shipment {


    private long shipmentId;
    private long accountId;
    private long shippingAddressId;
    private long orderLineId;
    private LocalDate shippingDate;
    private LocalDate deliveryDate;

    public Shipment() {
    }

    public Shipment(long shipmentId, long accountId, long shippingAddressId, long orderLineId, LocalDate shippingDate, LocalDate deliveryDate) {
        this.shipmentId = shipmentId;
        this.accountId = accountId;
        this.shippingAddressId = shippingAddressId;
        this.orderLineId = orderLineId;
        this.shippingDate = shippingDate;
        this.deliveryDate = deliveryDate;
    }

    public long getShipmentId() {
        return shipmentId;
    }

    public void setShipmentId(long shipmentId) {
        this.shipmentId = shipmentId;
    }

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    public long getShippingAddressId() {
        return shippingAddressId;
    }

    public void setShippingAddressId(long shippingAddressId) {
        this.shippingAddressId = shippingAddressId;
    }

    public long getOrderLineId() {
        return orderLineId;
    }

    public void setOrderLineId(long orderLineId) {
        this.orderLineId = orderLineId;
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
