package com.solstice.shipmentservice.domain;

public class OrderLine {


    private long orderLineId;
    private long productId;
    private int quantity;
    private double price;
    private double totalPrice;
    private long shipmentId;



    public OrderLine() {

    }

    public OrderLine(long orderLineId, long productId, int quantity, double price, double totalPrice, long shipmentId) {
        this.orderLineId = orderLineId;
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
        this.totalPrice = totalPrice;
        this.shipmentId = shipmentId;
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
        return totalPrice;
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
