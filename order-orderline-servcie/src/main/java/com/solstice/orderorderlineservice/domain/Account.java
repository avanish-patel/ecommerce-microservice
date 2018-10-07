package com.solstice.orderorderlineservice.domain;

import java.util.HashSet;
import java.util.Set;


public class Account {

    private long accountId;
    private String firstName;
    private String lastName;
    private String email;
    private Set<Address> addresses = new HashSet<Address>();

    public Account() {
    }

    public Account(long accountId, String firstName, String lastName, String email, Set<Address> addresses) {
        this.accountId = accountId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.addresses = addresses;
    }

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(Set<Address> addresses) {
        this.addresses = addresses;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountId=" + accountId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", addresses=" + addresses +
                '}';
    }
}
