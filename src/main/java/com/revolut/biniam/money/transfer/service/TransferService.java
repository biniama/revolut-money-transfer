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

/**
 * @author Biniam Asnake
 */
public class TransferService {

    private Map<Long, Transfer> transfers = DatabaseService.getTransfers();

    private AccountService accountService = AccountService.getInstance();

    private static TransferService instance;

    public static synchronized TransferService getInstance() {

        if (instance == null) {
            instance = new TransferService();
        }
        return instance;
    }

    private TransferService() {}

    public List<Transfer> getAllTransfers() {

        return new ArrayList<Transfer>(transfers.values());
    }

    public Transfer getTransfer(Long id) {
        return transfers.get(id);
    }

    public Transfer transfer(Transfer transfer) {

        Account sender = transfer.getSender();
        Account receiver = transfer.getReceiver();

        if (sender == null || receiver == null) {
            throw new AccountException("Either sender or receiver account is invalid", ACCOUNT_NOT_FOUND_EXCEPTION);
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
            throw new TransferException("Insufficient balance", INSUFFICIENT_BALANCE_EXCEPTION);
        }

    }

    @Deprecated
    public Transfer transfer(TransferDto transferDto) {

        Account senderAccount = accountService.getAccount(transferDto.getSenderId());

        Account receiverAccount = accountService.getAccount(transferDto.getReceiverId());

        senderAccount.setBalance(senderAccount.getBalance() - transferDto.getAmount());
        accountService.updateAccount(senderAccount);

        receiverAccount.setBalance(receiverAccount.getBalance() + transferDto.getAmount());
        accountService.updateAccount(receiverAccount);

        Transfer transfer = new Transfer(transfers.size() + 1L,
                                            senderAccount,
                                            receiverAccount,
                                            transferDto.getAmount(),
                                            TransferStatus.SUCCESSFUL);

        transfers.put(transfer.getId(), transfer);

        return transfer;
    }

    public Transfer updateTransfer(Transfer transfer) {

        if (transfer.getId() == null || transfer.getId() <= 0) {
            return null;
        }

        transfers.put(transfer.getId(), transfer);
        return transfer;
    }

    public Transfer removeTransfer(Long id) {
        return transfers.remove(id);
    }
}
