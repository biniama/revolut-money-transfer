package com.revolut.biniam.money.transfer.exception;

/**
 * Definition of all Account related exceptions with specific message and code
 *
 * @author Biniam Asnake.
 */
public class AccountException extends ServiceException {

    public static final Integer ACCOUNT_NOT_FOUND_EXCEPTION = 2000;
    public static final Integer INVALID_ACCOUNT_EXCEPTION = 2001;

    public AccountException(String message, Integer code) {
        super(message, code);
    }
}