package com.revolut.biniam.money.transfer.service;

import com.revolut.biniam.money.transfer.model.Account;
import com.revolut.biniam.money.transfer.model.Transfer;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Biniam Asnake
 */

//TODO: NOT THREAD SAFE
public class DatabaseService {

    private static Map<Long, Account> accounts = new HashMap<Long, Account>();
    private static Map<Long, Transfer> transfers = new HashMap<Long, Transfer>();

    public static Map<Long, Account> getAccounts() {
        return accounts;
    }

    public static Map<Long, Transfer> getTransfers() {
        return transfers;
    }
}
