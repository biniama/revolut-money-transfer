package com.revolut.biniam.money.transfer.controller;

import com.revolut.biniam.money.transfer.dto.TransferDto;
import com.revolut.biniam.money.transfer.model.Transfer;
import com.revolut.biniam.money.transfer.service.MapperService;
import com.revolut.biniam.money.transfer.service.TransferService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * API endpoints for performing CRUD operation on Money Transfer
 *
 * @author Biniam Asnake
 */
@Path("transfer")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TransferController {

    private TransferService transferService = TransferService.getInstance();

    private MapperService mapperService = new MapperService();

    @GET
    public List<Transfer> getAllTransfers() {

        return transferService.getAllTransfers();
    }

    @POST
    public Transfer addTransfer(TransferDto transferDto) {

        Transfer transfer = mapperService.convertTransferDtoToTransferObject(transferDto);
        return transferService.transfer(transfer);
    }

    @GET
    @Path("{transferId}")
    public Transfer getTransfer(@PathParam("transferId") Long id) {

        return transferService.getTransfer(id);
    }

    @PUT
    @Path("{transferId}")
    public Transfer updateTransfer(@PathParam("transferId") Long id, Transfer transfer) {

        transfer.setId(id);
        return transferService.updateTransfer(transfer);
    }

    @DELETE
    @Path("{transferId}")
    public Transfer deleteTransfer(@PathParam("transferId") Long id) {

        return transferService.removeTransfer(id);
    }
}
