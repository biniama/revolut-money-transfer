package com.revolut.biniam.money.transfer.controller;

import com.revolut.biniam.money.transfer.model.Account;
import com.revolut.biniam.money.transfer.service.AccountService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * API endpoints for performing CRUD operation on Account
 *
 * @author Biniam Asnake
 */
@Path("account")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AccountController {

    private AccountService accountService = AccountService.getInstance();

    @GET
    @ApiOperation(value = "Retrieves all accounts registered until now",
            response = Account.class,
            position = 1,
            responseContainer = "List")
    public List<Account> getAllAccounts() {

        return accountService.getAllAccounts();
    }

    @POST
    @ApiOperation(value = "Create an account",
            position = 2)
    public Account addAccount(@ApiParam(value = "Created account data", required = true) Account account) {

        return accountService.addAccount(account);
    }

    @GET
    @Path("{accountId}")
    @ApiOperation(value = "Retrieves account based on id",
            response = Account.class,
            position = 3)
    @ApiResponses(value = { @ApiResponse(code = 400, message = "Invalid account ID supplied") })
    public Account getAccount(
            @ApiParam(value = "account id to retrieve", required = true) @PathParam("accountId") Long id) {

        return accountService.getAccount(id);
    }

    @PUT
    @Path("{accountId}")
    @ApiOperation(value = "Updates account",
            notes = "Given the id and the updated account data, this operation makes modifications to an existing object",
            position = 4)
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Invalid account supplied"),
            @ApiResponse(code = 404, message = "account not found") })
    public Account updateAccount(
            @ApiParam(value = "account id that needs to be deleted", required = true) @PathParam("accountId") Long id,
            @ApiParam(value = "Updated account object", required = true) Account account) {

        account.setId(id);
        return accountService.updateAccount(account);
    }

    @DELETE
    @Path("{accountId}")
    @ApiOperation(value = "Delete account",
            notes = "Given the id of the account data to delete, it removes it from the in-memory datastore",
            position = 5)
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Invalid account supplied"),
            @ApiResponse(code = 404, message = "account not found") })
    public Account deleteAccount(
            @ApiParam(value = "account id that needs to be deleted", required = true) @PathParam("accountId") Long id) {

        return accountService.removeAccount(id);
    }
}
