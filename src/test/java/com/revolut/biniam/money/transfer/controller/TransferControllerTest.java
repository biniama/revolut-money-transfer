package com.revolut.biniam.money.transfer.controller;

import com.revolut.biniam.money.transfer.model.Transfer;
import com.revolut.biniam.money.transfer.service.AccountService;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Assert;
import org.junit.Test;

import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static org.junit.Assert.assertEquals;

/**
 * Tests for TransferController
 *
 * @author Biniam Asnake
 */
public class TransferControllerTest extends JerseyTest {

    private static final String PATH_TRANSFER = "transfer";

    private AccountService accountService = AccountService.getInstance();

    @Override
    protected Application configure() {
        return new ResourceConfig(TransferController.class);
    }

    @Test
    public void getAllTransfers() {

        final String response = target(PATH_TRANSFER)
                .request(MediaType.APPLICATION_JSON_TYPE)
                .accept(MediaType.APPLICATION_JSON_TYPE)
                .get(String.class);

        assertEquals("[{\"amount\":100.0,\"id\":1,\"receiver\":{\"balance\":70100.0,\"firstName\":\"Naod\",\"id\":4,\"lastName\":\"Biniam\",\"phoneNumber\":\"+4922222222222\"},\"sender\":{\"balance\":49900.0,\"firstName\":\"Hasset\",\"id\":3,\"lastName\":\"Biniam\",\"phoneNumber\":\"+491111111111\"},\"status\":\"SUCCESSFUL\"}]",
                response);
    }

    @Test
    public void shouldAddTransfer() {

        String data = "{" +
                "\"senderId\": 2," +
                "\"receiverId\": 3," +
                "\"amount\": 900.00" +
                "}";

        final Response response = target(PATH_TRANSFER)
                .request(MediaType.APPLICATION_JSON_TYPE)
                .accept(MediaType.APPLICATION_JSON_TYPE)
                .post(Entity.json(data));

        assertEquals(200, response.getStatus());
    }

    @Test
    public void shouldUpdateFirstObject() {

        Transfer transfer = new Transfer();
        transfer.setSender(accountService.getAccount(4L));
        transfer.setReceiver(accountService.getAccount(2L));
        transfer.setAmount(123.00);

        Response response = target(PATH_TRANSFER + "/5").request()
                .put(Entity.entity(transfer, MediaType.APPLICATION_JSON));

        assertEquals(200, response.getStatus());
    }

    @Test
    public void shouldDeleteAccount() {

        final String response = target(PATH_TRANSFER + "/1")
                .request()
                .delete(String.class);

        Assert.assertEquals("{\"amount\":100.0," +
                "\"id\":1," +
                "\"receiver\":{\"balance\":70100.0,\"firstName\":\"Naod\",\"id\":4,\"lastName\":\"Biniam\",\"phoneNumber\":\"+4922222222222\"}," +
                "\"sender\":{\"balance\":50800.0,\"firstName\":\"Hasset\",\"id\":3,\"lastName\":\"Biniam\",\"phoneNumber\":\"+491111111111\"}," +
                "\"status\":\"SUCCESSFUL\"}",
            response);
    }

    @Test(expected = InternalServerErrorException.class)
    public void shouldThrowExceptionWhenInvalidTransferIsGiven() {
        String response = target(PATH_TRANSFER + "/99").request().get(String.class);
        Assert.assertTrue("Transfer with id 99 is invalid".equals(response));
    }

    @Test
    public void shouldThrowExceptionWhenTransferringToSameAccount() {

        String data = "{" +
                "\"senderId\": 2," +
                "\"receiverId\": 2," +
                "\"amount\": 50.00" +
                "}";

        final Response response = target(PATH_TRANSFER)
                .request(MediaType.APPLICATION_JSON_TYPE)
                .accept(MediaType.APPLICATION_JSON_TYPE)
                .post(Entity.json(data));

        assertEquals(500, response.getStatus());
    }

    @Test
    public void shouldThrowExceptionIfBalanceIsInsufficient() {

        String data = "{" +
                "\"senderId\": 2," +
                "\"receiverId\": 2," +
                "\"amount\": 5000000.00" +
                "}";

        final Response response = target(PATH_TRANSFER)
                .request(MediaType.APPLICATION_JSON_TYPE)
                .accept(MediaType.APPLICATION_JSON_TYPE)
                .post(Entity.json(data));

        assertEquals(500, response.getStatus());
    }

    @Test
    public void shouldThrowExceptionIfAccountIsInvalid() {

        String data = "{" +
                "\"senderId\": 200," +
                "\"receiverId\": 300," +
                "\"amount\": 5000000.00" +
                "}";

        final Response response = target(PATH_TRANSFER)
                .request(MediaType.APPLICATION_JSON_TYPE)
                .accept(MediaType.APPLICATION_JSON_TYPE)
                .post(Entity.json(data));

        assertEquals(500, response.getStatus());
    }
}
