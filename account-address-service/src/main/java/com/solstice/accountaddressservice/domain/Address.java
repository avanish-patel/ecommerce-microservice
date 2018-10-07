package com.solstice.accountaddressservice.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long addressId;
    private String street;
    private String aptOrBuilding;
    private String city;
    private String state;
    private int zipCode;
    private String country;

    @ManyToOne
    @JsonBackReference
    private Account account;

    public Address() {
    }


    public long getAddressId() {
        return addressId;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public void setAddressId(long addressId) {
        this.addressId = addressId;
    }


    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getAptOrBuilding() {
        return aptOrBuilding;
    }

    public void setAptOrBuilding(String aptOrBuilding) {
        this.aptOrBuilding = aptOrBuilding;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
