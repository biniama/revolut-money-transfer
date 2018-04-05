package com.revolut.biniam.money.transfer.exception.model;

import lombok.Data;

/**
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
