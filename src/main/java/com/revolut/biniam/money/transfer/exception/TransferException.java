package com.revolut.biniam.money.transfer.exception;

/**
 * Definition of all Transfer related exceptions with specific message and code
 *
 * @author Biniam Asnake.
 */
public class TransferException extends ServiceException {

    public static final Integer TRANSFER_FAILED_EXCEPTION = 3000;
    public static final Integer INSUFFICIENT_BALANCE_EXCEPTION = 3001;
    public static final Integer SAME_ACCOUNT_TRANSFER_EXCEPTION = 3002;

    public TransferException(String message, Integer code) {
        super(message, code);
    }
}