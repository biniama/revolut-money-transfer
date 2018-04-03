package com.revolut.biniam.model;

import com.revolut.biniam.model.enumeration.TransferStatus;
import lombok.Data;

/**
 * @author Biniam Asnake
 */
@Data
public class Transfer {

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
}
