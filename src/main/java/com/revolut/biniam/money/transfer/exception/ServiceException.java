package com.revolut.biniam.money.transfer.exception;

/**
 * A super class for all exception Service classes
 *
 * @author Biniam Asnake
 */
public class ServiceException extends RuntimeException {

    public static final Integer INTERNAL_EXCEPTION = 1000;

    private final Integer code;

    public ServiceException(String message, Integer code) {
        super(message);
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

}