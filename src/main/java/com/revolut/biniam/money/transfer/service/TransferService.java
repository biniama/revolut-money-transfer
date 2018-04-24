package com.revolut.biniam.money.transfer.service;

import com.revolut.biniam.money.transfer.dto.TransferDto;
import com.revolut.biniam.money.transfer.exception.AccountException;
import com.revolut.biniam.money.transfer.exception.TransferException;
import com.revolut.biniam.money.transfer.model.Account;
import com.revolut.biniam.money.transfer.model.Transfer;
import com.revolut.biniam.money.transfer.model.enumeration.TransferStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.revolut.biniam.money.transfer.exception.AccountException.ACCOUNT_NOT_FOUND_EXCEPTION;
import static com.revolut.biniam.money.transfer.exception.TransferException.INSUFFICIENT_BALANCE_EXCEPTION;
import static com.revolut.biniam.money.transfer.exception.TransferException.SAME_ACCOUNT_TRANSFER_EXCEPTION;

/**
 * Transfer Service that defines the main business logic of creating, reading, updating and deleting transfers.
 *
 * @author Biniam Asnake
 */
public class TransferService {

    private Map<Long, Transfer> transfers = DatabaseService.getTransfers();

    private AccountService accountService = AccountService.getInstance();

    private static TransferService instance;

    /**
     * Creates a Singleton instance of TransferService.
     * This is very useful because we need a single instance of the service.
     *
     * @return single instance of TransferService
     */
    public static synchronized TransferService getInstance() {

        if (instance == null) {
            instance = new TransferService();
        }

        return instance;
    }

    private TransferService() {}

    /**
     * Get all transfers
     *
     * @return List of Transfers
     */
    public List<Transfer> getAllTransfers() {

        return new ArrayList<Transfer>(transfers.values());
    }

    /**
     * Retrieves transfer based on id
     *
     * @param id: Long variable representing the ID of the transfer
     * @return Transfer object if found. Otherwise, null
     */
    public Transfer getTransfer(Long id) {

        Transfer transfer = transfers.get(id);

        if (transfer == null) {
            throw new AccountException("Transfer with id " + id.toString() + " is invalid.", ACCOUNT_NOT_FOUND_EXCEPTION);
        }

        return transfer;
    }

    /**
     * Main business logic for transferring money from one account to another.
     *
     * Requirements for successful money transfer:
     *  1. Sender AND receiver accounts must not be null
     *  2. Both sender and receiver cannot be the same account
     *  3. The Senders account balance should be equal or greater than the transfer amount.
     *
     *  Otherwise, money transfer is not possible.
     *
     * @param transfer: transfer object containing information about the money transfer
     * @return The created Transfer object if everything is successful.
     * Otherwise, either AccountException or TransferException will be thrown.
     */
    public Transfer transfer(Transfer transfer) {

        transfer.setStatus(TransferStatus.PROCESSING);

        Account sender = transfer.getSender();
        Account receiver = transfer.getReceiver();

        if (sender == null || receiver == null) {
            throw new AccountException("Either sender or receiver account is invalid", ACCOUNT_NOT_FOUND_EXCEPTION);
        }

        if (sender.getId().equals(receiver.getId())) {
            throw new TransferException("Both sender and receiver cannot be the same account", SAME_ACCOUNT_TRANSFER_EXCEPTION);
        }

        if (sender.getBalance() >= transfer.getAmount()) {

            sender.setBalance(sender.getBalance() - transfer.getAmount());
            accountService.updateAccount(sender);

            receiver.setBalance(receiver.getBalance() + transfer.getAmount());
            accountService.updateAccount(receiver);

            transfer.setId(transfers.size() + 1L);
            transfer.setStatus(TransferStatus.SUCCESSFUL);
            transfers.put(transfer.getId(), transfer);

            return transfer;

        } else {
            transfer.setStatus(TransferStatus.FAILED);
            throw new TransferException("Insufficient balance", INSUFFICIENT_BALANCE_EXCEPTION);
        }
    }

    /**
     * Updates Transfer if it is available in the {code}transfer{code} map
     *
     * @param transfer: Transfer to be updated
     * @return The updated Transfer object
     */
    public Transfer updateTransfer(Transfer transfer) {

        if (transfer.getId() == null || transfer.getId() <= 0) {
            return null;
        }

        transfers.put(transfer.getId(), transfer);
        return transfer;
    }

    /**
     * Deletes transfer from the map based on id
     *
     * @param id: Id of the transfer to be deleted
     * @return The deleted Transfer object
     */
    public Transfer removeTransfer(Long id) {
        return transfers.remove(id);
    }
}
