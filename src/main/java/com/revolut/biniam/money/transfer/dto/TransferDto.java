package com.revolut.biniam.money.transfer.dto;

import lombok.Data;

import java.util.Date;

/**
 * Data Transfer Object (DTO) for taking input from API
 *
 * @author Biniam Asnake
 */
@Data
public class TransferDto {

    private Long senderId;

    private Long receiverId;

    private Double amount;

    private Date date;

    public TransferDto() {
    }

    public TransferDto(Long senderId, Long receiverId, Double amount, Date date) {
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.amount = amount;
        this.date = date;
    }
}
