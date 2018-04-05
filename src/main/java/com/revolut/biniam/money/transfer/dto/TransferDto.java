package com.revolut.biniam.money.transfer.dto;

import lombok.Data;

/**
 * @author Biniam Asnake
 */
@Data
public class TransferDto {

    private Long senderId;

    private Long receiverId;

    private Double amount;

    public TransferDto() {
    }

    public TransferDto(Long senderId, Long receiverId, Double amount) {
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.amount = amount;
    }
}
