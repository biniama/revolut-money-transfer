package com.revolut.biniam.money.transfer.service;

import com.revolut.biniam.money.transfer.dto.TransferDto;
import com.revolut.biniam.money.transfer.model.Account;
import com.revolut.biniam.money.transfer.model.Transfer;
import com.revolut.biniam.money.transfer.model.enumeration.TransferStatus;

/**
 * Service used to define methods for mapping a DTO to a model object
 *
 * @author Biniam Asnake
 */
public class MapperService {

    private AccountService accountService;

    public MapperService() {
        accountService = AccountService.getInstance();
    }

    /**
     * Converts TransferDTO to Transfer object
     *
     * @param transferDto: input given in TransferDTO format
     * @return Transfer object which is created by mapping the field values of the TransferDTO
     */
    public Transfer convertTransferDtoToTransferObject(TransferDto transferDto) {

        Account senderAccount = accountService.getAccount(transferDto.getSenderId());

        Account receiverAccount = accountService.getAccount(transferDto.getReceiverId());

        return new Transfer(senderAccount, receiverAccount, transferDto.getAmount(),
                transferDto.getDate(), TransferStatus.PENDING);
    }
}
