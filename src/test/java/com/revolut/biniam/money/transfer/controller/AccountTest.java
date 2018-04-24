package com.revolut.biniam.money.transfer.controller;

import com.revolut.biniam.money.transfer.model.Account;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Tests for Account domain/model
 *
 * @author Biniam Asnake
 */
public class AccountTest {

    @Test
    public void shouldReturnTrueForAccountsWithSameId() {

        Account account1 = new Account(1L, "Biniam", "Asnake", "+4917621003292", 1000.00);
        Account account2 = new Account(1L, "Biniam", "Asnake", "+4917621003292", 1000.00);

        assertEquals(account1, account2);
    }

    @Test
    public void shouldReturnFalseForAccountsWithDifferentId() {

        Account account1 = new Account(1L, "Biniam", "Asnake", "+4917621003292", 1000.00);
        Account account2 = new Account(2L, "Biniam", "Asnake", "+4917621003292", 1000.00);

        assertNotEquals(account1, account2);
    }
}
