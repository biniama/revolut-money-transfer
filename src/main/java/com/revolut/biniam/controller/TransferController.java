package com.revolut.biniam.controller;

import com.revolut.biniam.model.Transfer;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * @author Biniam Asnake
 */
@Path("transfer")
public class TransferController {

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Transfer transferMoney() {

        return new Transfer();
    }
}
