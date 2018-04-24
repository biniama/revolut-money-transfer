package com.revolut.biniam.money.transfer.controller;

import com.revolut.biniam.money.transfer.exception.AccountException;
import com.revolut.biniam.money.transfer.exception.TransferException;
import com.revolut.biniam.money.transfer.model.Transfer;
import com.revolut.biniam.money.transfer.service.AccountService;
import com.revolut.biniam.money.transfer.service.TransferService;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Tests for TransferService
 *
 * @author Biniam Asnake
 */
public class TransferServiceTest {

    private AccountService accountService = AccountService.getInstance();
    private TransferService transferService = TransferService.getInstance();

    @Test
    public void shouldAddTransferUsingTransferObject() {

        Transfer transfer = new Transfer();
        transfer.setSender(accountService.getAccount(3L));
        transfer.setReceiver(accountService.getAccount(4L));
        transfer.setAmount(100.00);

        Transfer transferResult = transferService.transfer(transfer);

        assertEquals(transferResult, transfer);
    }

    @Test(expected = TransferException.class)
    public void shouldThrowExceptionWhenTransferringToSameAccount() {

        Transfer transfer = new Transfer();
        transfer.setSender(accountService.getAccount(2L));
        transfer.setReceiver(accountService.getAccount(2L));
        transfer.setAmount(100.00);

        transferService.transfer(transfer);
    }

    @Test(expected = TransferException.class)
    public void shouldThrowExceptionIfBalanceIsInsufficient() {

        Transfer transfer = new Transfer();
        transfer.setSender(accountService.getAccount(2L));
        transfer.setReceiver(accountService.getAccount(2L));
        transfer.setAmount(100000000.00);

        transferService.transfer(transfer);
    }

    @Test(expected = AccountException.class)
    public void shouldThrowExceptionIfAccountIsInvalid() {

        Transfer transfer = new Transfer();
        transfer.setSender(accountService.getAccount(2000L));
        transfer.setReceiver(accountService.getAccount(3000L));
        transfer.setAmount(100.00);

        transferService.transfer(transfer);
    }
}
