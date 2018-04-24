package com.revolut.biniam.money.transfer.service;

import com.revolut.biniam.money.transfer.exception.AccountException;
import com.revolut.biniam.money.transfer.model.Account;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.revolut.biniam.money.transfer.exception.AccountException.ACCOUNT_NOT_FOUND_EXCEPTION;
import static com.revolut.biniam.money.transfer.exception.AccountException.INVALID_ACCOUNT_EXCEPTION;

/**
 * Account Service that defines the main business logic of creating, reading, updating and deleting accounts.
 *
 * @author Biniam Asnake
 */
public class AccountService {

    private Map<Long, Account> accounts = DatabaseService.getAccounts();

    private static AccountService instance;

    /**
     * Creates a Singleton instance of AccountService.
     * This is very useful because we need a single instance of the service.
     *
     * @return single instance of AccountService
     */
    public static synchronized AccountService getInstance() {

        if (instance == null) {
            instance = new AccountService();
        }

        return instance;
    }

    /**
     * Constructor for AccountService which is used to initialize sample data
     */
    private AccountService() {

         accounts.put(1L, new Account(1L, "Biniam", "Asnake", "+4917621003292", 1000.00));
         accounts.put(2L, new Account(2L, "Kidan", "Lakew", "+4917621003288", 8000.00));
         accounts.put(3L, new Account(3L, "Hasset", "Biniam", "+491111111111", 50000.00));
         accounts.put(4L, new Account(4L, "Naod", "Biniam", "+4922222222222", 70000.00));
    }

    /**
     * Get all accounts
     *
     * @return List of Accounts
     */
    public List<Account> getAllAccounts() {

        return new ArrayList<Account>(accounts.values());
    }

    /**
     * Retrieves account based on id
     *
     * @param id: Long variable representing the ID of the account
     * @return Account object if found. Otherwise, null
     */
    public Account getAccount(Long id) {

        Account account = accounts.get(id);

        if (account == null) {
            throw new AccountException("Account with id " + id.toString() + " is invalid.",
                    ACCOUNT_NOT_FOUND_EXCEPTION);
        }

        return account;
    }

    /**
     * Adds Account to the {code}accounts{code} map
     *
     * @param account: Account to be added
     * @return The added Account object
     */
    public Account addAccount(Account account) {
        account.setId(accounts.size() + 1L);
        accounts.put(account.getId(), account);

        return account;
    }

    /**
     * Updates Account if it is available in the {code}accounts{code} map
     *
     * @param account: Account to be updated
     * @return The updated Account object
     */
    public Account updateAccount(Account account) {

        if (account.getId() == null || account.getId() <= 0) {
            throw new AccountException("Invalid account id", INVALID_ACCOUNT_EXCEPTION);
        }

        accounts.put(account.getId(), account);
        return account;
    }

    /**
     * Deletes account from the map based on id
     *
     * @param id: Id of the account to be deleted
     * @return The deleted Account object
     */
    public Account removeAccount(Long id) {
        return accounts.remove(id);
    }
}
