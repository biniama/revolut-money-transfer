package com.revolut.biniam.money.transfer.model;

import com.revolut.biniam.money.transfer.model.enumeration.TransferStatus;
import lombok.Data;

import java.util.Date;
import java.util.Objects;

/**
 * Money Transfer model/domain
 *
 * @author Biniam Asnake
 */
@Data
public class Transfer {

    private Long id;

    private Account sender;

    private Account receiver;

    private Double amount;

    private Date date;

    private TransferStatus status;

    public Transfer() {
    }

    public Transfer(Account sender, Account receiver, Double amount, Date date, TransferStatus status) {
        this.sender = sender;
        this.receiver = receiver;
        this.amount = amount;
        this.date = date;
        this.status = status;
    }

    public Transfer(Long id, Account sender, Account receiver, Double amount, Date date, TransferStatus status) {
        this.id = id;
        this.sender = sender;
        this.receiver = receiver;
        this.amount = amount;
        this.date = date;
        this.status = status;
    }

    /**
     * Overrriden equals and hashCode methods to compare two Transfer objects based on sender, receiver, amount and date.
     *
     * @param object: Account to compare
     * @return true if objects are equal. Otherwise, false.
     */
    @Override
    public boolean equals(Object object) {

        if (object == this) return true;
        if (!(object instanceof Transfer)) {
            return false;
        }
        Transfer transfer = (Transfer) object;
        return Objects.equals(sender, transfer.sender) &&
                Objects.equals(receiver, transfer.receiver) &&
                Objects.equals(amount, transfer.amount) &&
                Objects.equals(date, transfer.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sender, receiver, amount, date);
    }
}
