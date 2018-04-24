package com.revolut.biniam.money.transfer.controller;

import com.revolut.biniam.money.transfer.model.Account;
import com.revolut.biniam.money.transfer.service.AccountService;

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
    public List<Account> getAllAccounts() {

        return accountService.getAllAccounts();
    }

    @POST
    public Account addAccount(Account account) {

        return accountService.addAccount(account);
    }

    @GET
    @Path("{accountId}")
    public Account getAccount(@PathParam("accountId") Long id) {

        return accountService.getAccount(id);
    }

    @PUT
    @Path("{accountId}")
    public Account updateAccount(@PathParam("accountId") Long id, Account account) {

        account.setId(id);
        return accountService.updateAccount(account);
    }

    @DELETE
    @Path("{accountId}")
    public Account deleteAccount(@PathParam("accountId") Long id) {

        return accountService.removeAccount(id);
    }
}
