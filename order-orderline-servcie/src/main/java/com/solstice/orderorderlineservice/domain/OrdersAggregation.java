package com.solstice.orderorderlineservice.domain;

import java.util.Date;
import java.util.List;

public class OrdersAggregation {

    private long orderNo;
    private Address shippingAddress;
    private double totalPrice;
    private List<OrderLineItems> orderLineItems;


    public OrdersAggregation() {
    }

    public OrdersAggregation(long orderNo, Address shippingAddress, double totalPrice, List<OrderLineItems> orderLineItems) {
        this.orderNo = orderNo;
        this.shippingAddress = shippingAddress;
        this.totalPrice = totalPrice;
        this.orderLineItems = orderLineItems;
    }

    public long getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(long orderNo) {
        this.orderNo = orderNo;
    }

    public Address getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(Address shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<OrderLineItems> getOrderLineItems() {
        return orderLineItems;
    }

    public void setOrderLineItems(List<OrderLineItems> orderLineItems) {
        this.orderLineItems = orderLineItems;
    }


}
