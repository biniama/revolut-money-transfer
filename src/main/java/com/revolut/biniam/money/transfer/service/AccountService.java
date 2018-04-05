package com.revolut.biniam.money.transfer.service;

import com.revolut.biniam.money.transfer.exception.AccountException;
import com.revolut.biniam.money.transfer.model.Account;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.revolut.biniam.money.transfer.exception.AccountException.INVALID_ACCOUNT_EXCEPTION;

/**
 * @author Biniam Asnake
 */
public class AccountService {

    private Map<Long, Account> accounts = DatabaseService.getAccounts();

    private static AccountService instance;

    public static synchronized AccountService getInstance() {

        if (instance == null) {
            instance = new AccountService();
        }
        return instance;
    }

    private AccountService() {

         accounts.put(1L, new Account(1L, "Biniam", "Asnake", "+4917621003292", 1000.00));
         accounts.put(2L, new Account(2L, "Kidan", "Lakew", "+4917621003288", 8000.00));
         accounts.put(3L, new Account(3L, "Hasset", "Biniam", "+491111111111", 50000.00));
         accounts.put(4L, new Account(4L, "Barok", "Biniam", "+4922222222222", 70000.00));
    }

    public List<Account> getAllAccounts() {

        return new ArrayList<Account>(accounts.values());
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
            throw new AccountException("Invalid account id", INVALID_ACCOUNT_EXCEPTION);
        }

        accounts.put(account.getId(), account);
        return account;
    }

    public Account removeAccount(Long id) {
        return accounts.remove(id);
    }
}
