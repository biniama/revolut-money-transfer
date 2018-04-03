package com.revolut.biniam.model;

import lombok.Data;

/**
 * @author Biniam Asnake
 */
@Data
public class Account {

    private Long id;

    private String firstName;

    private String lastName;

    private String phoneNumber;

    private Double balance;

    public Account() {
    }

    public Account(Long id, String firstName, String lastName, String phoneNumber, Double balance) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.balance = balance;
    }
}
