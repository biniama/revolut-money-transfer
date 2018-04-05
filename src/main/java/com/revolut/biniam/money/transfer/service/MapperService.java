package com.revolut.biniam.money.transfer.service;

import com.revolut.biniam.money.transfer.dto.TransferDto;
import com.revolut.biniam.money.transfer.model.Account;
import com.revolut.biniam.money.transfer.model.Transfer;
import com.revolut.biniam.money.transfer.model.enumeration.TransferStatus;

/**
 * @author Biniam Asnake
 */
public class MapperService {

    private AccountService accountService = AccountService.getInstance();

    public Transfer convertTransferDtoToTransferObject(TransferDto transferDto) {

        Account senderAccount = accountService.getAccount(transferDto.getSenderId());

        Account receiverAccount = accountService.getAccount(transferDto.getReceiverId());

        return new Transfer(senderAccount, receiverAccount, transferDto.getAmount(), TransferStatus.PENDING);
    }
}
