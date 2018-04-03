package com.revolut.biniam.service;

import com.revolut.biniam.model.Account;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Biniam Asnake
 */
public class AccountService {

    private Map<Long, Account> accounts = DatabaseService.getAccounts();

    public AccountService() {

        accounts.put(1L, new Account(1L, "Biniam", "Asnake", "+4917621003292", 1000.00));
        accounts.put(2L, new Account(2L, "Kidan", "Lakew", "+4917621003288", 8000.00));
    }

    public List<Account> getAllAccounts() {

        return new ArrayList<>(accounts.values());
    }

    public Account getAccount(Long id) {
        return accounts.get(id);
    }

    public Account addAccount(Account account) {
        account.setId(accounts.size() + 1L);
        accounts.put(account.getId(), account);

        return account;
    }

    public Account updateAccount(Account account) {

        if (account.getId() == null || account.getId() <= 0) {
            return null;
        }

        accounts.put(account.getId(), account);
        return account;
    }

    public Account removeAccount(Long id) {
        return accounts.remove(id);
    }
}
