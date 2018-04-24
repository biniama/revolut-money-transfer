package com.revolut.biniam.money.transfer.exception.model;

import lombok.Data;

/**
 * Error Message model for representing text message and error code.
 * This error message will be displayed to the users if something wrong happens.
 * It is very important to show meaningful message to users instead of 500 pages.
 *
 * @author Biniam Asnake
 */
@Data
public class ErrorMessage {

    private String message;

    private Integer code;

    public ErrorMessage() {
    }

    public ErrorMessage(String message, Integer code) {
        this.message = message;
        this.code = code;
    }
}
