package com.revolut.biniam.controller;

import com.revolut.biniam.model.Account;
import com.revolut.biniam.service.AccountService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * @author Biniam Asnake
 */
@Path("account")
public class AccountController {

    AccountService accountService = new AccountService();

    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Account> getAllAccounts() {

        return accountService.getAllAccounts();
    }

}
