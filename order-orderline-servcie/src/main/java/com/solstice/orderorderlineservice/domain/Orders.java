package com.solstice.orderorderlineservice.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;

import java.time.LocalDate;
import java.util.Set;

@Entity
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ordersId;
    private long accountId;
    private LocalDate orderDate;
    private long shippingAddressId;
    @Transient
    @JsonIgnore
    private double total;

    @JsonIgnore
    @OneToMany(mappedBy = "orders", fetch = FetchType.EAGER)
    @JsonManagedReference
    private Set<OrderLine> orderLineSet;

    public Orders() {
    }

    public long getOrdersId() {
        return ordersId;
    }

    public void setOrdersId(long ordersId) {
        this.ordersId = ordersId;
    }

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public long getShippingAddressId() {
        return shippingAddressId;
    }

    public void setShippingAddressId(long shippingAddressId) {
        this.shippingAddressId = shippingAddressId;
    }

    public double getTotal() {

        total = 0;
        for(OrderLine orderLine: orderLineSet ){

            double lineTotal = orderLine.getTotalPrice();
            total = total + lineTotal;
        }

        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Set<OrderLine> getOrderLineSet() {
        return orderLineSet;
    }

    public void setOrderLineSet(Set<OrderLine> orderLineSet) {
        this.orderLineSet = orderLineSet;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "ordersId=" + ordersId +
                ", accountId=" + accountId +
                ", orderDate=" + orderDate +
                ", shippingAddressId=" + shippingAddressId +
                ", total=" + total +
                ", orderLineSet=" + orderLineSet +
                '}';
    }
}
