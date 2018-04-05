package com.revolut.biniam.money.transfer.model;

import com.revolut.biniam.money.transfer.model.enumeration.TransferStatus;
import lombok.Data;

/**
 * @author Biniam Asnake
 */
@Data
public class Transfer {

    private Long id;

    private Account sender;

    private Account receiver;

    private Double amount;

    private TransferStatus status;

    public Transfer() {
    }

    public Transfer(Account sender, Account receiver, Double amount, TransferStatus status) {
        this.sender = sender;
        this.receiver = receiver;
        this.amount = amount;
        this.status = status;
    }

    public Transfer(Long id, Account sender, Account receiver, Double amount, TransferStatus status) {
        this.id = id;
        this.sender = sender;
        this.receiver = receiver;
        this.amount = amount;
        this.status = status;
    }
}
