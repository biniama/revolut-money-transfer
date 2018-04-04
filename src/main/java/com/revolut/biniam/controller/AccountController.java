package com.revolut.biniam.controller;

import com.revolut.biniam.model.Account;
import com.revolut.biniam.service.AccountService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * @author Biniam Asnake
 */
@Path("account")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AccountController {

    AccountService accountService = new AccountService();

    @GET
    public List<Account> getAllAccounts() {

        return accountService.getAllAccounts();
    }

    @POST
    public Account getAccount(Account account) {

        return accountService.addAccount(account);
    }

    @GET
    @Path("{accountId}")
    public Account getAccounts(@PathParam("accountId") Long id) {

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
    public void deleteAccount(@PathParam("accountId") Long id) {

        accountService.removeAccount(id);
    }
}
