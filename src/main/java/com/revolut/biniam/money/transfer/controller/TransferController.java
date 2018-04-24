package com.revolut.biniam.money.transfer.controller;

import com.revolut.biniam.money.transfer.dto.TransferDto;
import com.revolut.biniam.money.transfer.model.Transfer;
import com.revolut.biniam.money.transfer.service.MapperService;
import com.revolut.biniam.money.transfer.service.TransferService;
import io.swagger.annotations.*;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * API endpoints for performing CRUD operation on Money Transfer
 *
 * @author Biniam Asnake
 */
@Path("transfer")
@Api(value="/transfer", description = "Operations about money transfer")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TransferController {

    private TransferService transferService = TransferService.getInstance();

    private MapperService mapperService = new MapperService();

    @GET
    @ApiOperation(value = "Retrieves all transfers registered until now",
            response = Transfer.class,
            position = 1,
            responseContainer = "List")
    public List<Transfer> getAllTransfers() {

        return transferService.getAllTransfers();
    }

    @POST
    @ApiOperation(value = "Create a money transfer operation",
            notes = "Requirements for successful money transfer:\n" +
                    "     *  1. Sender AND receiver accounts must not be null\n" +
                    "     *  2. Both sender and receiver cannot be the same account\n" +
                    "     *  3. The Senders account balance should be equal or greater than the transfer amount.\n" +
                    "     *  Otherwise, money transfer is not possible.",
            position = 2)
    public Transfer addTransfer(@ApiParam(value = "Created transfer DTO (data transfer object)", required = true) TransferDto transferDto) {

        Transfer transfer = mapperService.convertTransferDtoToTransferObject(transferDto);
        return transferService.transfer(transfer);
    }

    @GET
    @Path("{transferId}")
    @ApiOperation(value = "Retrieves transfer based on id",
            response = Transfer.class,
            position = 3)
    @ApiResponses(value = { @ApiResponse(code = 400, message = "Invalid transfer ID supplied") })
    public Transfer getTransfer(
            @ApiParam(value = "transfer id to retrieve", required = true) @PathParam("transferId") Long id) {

        return transferService.getTransfer(id);
    }

    @PUT
    @Path("{transferId}")
    @ApiOperation(value = "Updates transfer",
            notes = "Given the id and the updated transfer data, this operation makes modifications to an existing object",
            position = 4)
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Invalid transfer supplied"),
            @ApiResponse(code = 404, message = "transfer not found") })
    public Transfer updateTransfer(
            @ApiParam(value = "transfer id that needs to be deleted", required = true) @PathParam("transferId") Long id,
            @ApiParam(value = "Updated transfer object", required = true) Transfer transfer) {

        transfer.setId(id);
        return transferService.updateTransfer(transfer);
    }

    @DELETE
    @Path("{transferId}")
    @ApiOperation(value = "Delete transfer",
            notes = "Given the id of the transfer data to delete, it removes it from the in-memory datastore",
            position = 5)
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Invalid transfer supplied"),
            @ApiResponse(code = 404, message = "transfer not found") })
    public Transfer deleteTransfer(
            @ApiParam(value = "transfer id that needs to be deleted", required = true) @PathParam("transferId") Long id) {

        return transferService.removeTransfer(id);
    }
}
