package com.revolut.biniam.money.transfer.model;

import lombok.Data;

import java.util.Objects;

/**
 * Account model/domain that represents a bank account
 *
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

    /**
     * Overrriden equals and hashCode methods to compare two Account objects based on id
     *
     * @param object
     * @return true if objects are equal. Otherwise, false.
     */
    @Override
    public boolean equals(Object object) {

        if (object == this) return true;

        if (!(object instanceof Account)) {
            return false;
        }

        Account account = (Account) object;

        return Objects.equals(id, account.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }

}
