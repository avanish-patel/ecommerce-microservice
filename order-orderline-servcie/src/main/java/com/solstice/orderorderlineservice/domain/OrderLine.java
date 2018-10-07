package com.solstice.orderorderlineservice.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.Formula;

import javax.persistence.*;

@Entity
public class OrderLine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long orderLineId;
    private long productId;
    private int quantity;
    private double price;
    @Transient
    private double totalPrice;
    private long shipmentId;

    @ManyToOne
    @JsonBackReference
    private Orders orders;

    public OrderLine() {

    }

    public Orders getOrders() {
        return orders;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }

    public long getOrderLineId() {
        return orderLineId;
    }

    public void setOrderLineId(long orderLineId) {
        this.orderLineId = orderLineId;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


    public double getTotalPrice() {
        return quantity * price ;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public long getShipmentId() {
        return shipmentId;
    }

    public void setShipmentId(long shipmentId) {
        this.shipmentId = shipmentId;
    }
}
