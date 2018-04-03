package com.revolut.biniam.service;

import com.revolut.biniam.model.Account;
import com.revolut.biniam.model.Transfer;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Biniam Asnake
 */

//TODO: NOT THREAD SAFE
public class DatabaseService {

    private static Map<Long, Account> accounts = new HashMap<>();
    private static Map<Long, Transfer> transfers = new HashMap<>();

    public static Map<Long, Account> getAccounts() {
        return accounts;
    }

    public static Map<Long, Transfer> getTransfers() {
        return transfers;
    }
}
