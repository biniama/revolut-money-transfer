package com.revolut.biniam.money.transfer.controller;

import com.revolut.biniam.money.transfer.model.Account;
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
 * Tests for AccountController
 *
 * @author Biniam Asnake
 */
public class AccountControllerTest extends JerseyTest {

    private static final String PATH_ACCOUNT = "account";

    @Override
    protected Application configure() {
        return new ResourceConfig(AccountController.class);
    }

    @Test
    public void getAllAccounts() {

        final String response = target(PATH_ACCOUNT)
                .request(MediaType.APPLICATION_JSON_TYPE)
                .accept(MediaType.APPLICATION_JSON_TYPE)
                .get(String.class);

        assertEquals("[{\"balance\":8000.0,\"firstName\":\"Kidan\",\"id\":2,\"lastName\":\"Lakew\",\"phoneNumber\":\"+4917621003288\"}," +
                "{\"balance\":49900.0,\"firstName\":\"Hasset\",\"id\":3,\"lastName\":\"Biniam\",\"phoneNumber\":\"+491111111111\"}," +
                "{\"balance\":3888.0,\"firstName\":\"John\",\"id\":4,\"lastName\":\"Doe\",\"phoneNumber\":\"555-456-9877\"}," +
                "{\"balance\":0.0,\"firstName\":\"New first name\",\"id\":10,\"lastName\":\"New last name\",\"phoneNumber\":\"000000000\"}]",
                response);
    }

    @Test
    public void shouldAddAccount() {

        String data = "{" +
            "\"firstName\":\"Bole\", " +
                "\"lastName\": \"Lale\", " +
                "\"phoneNumber\": \"+494444444444\", " +
                "\"balance\": 3500.00 " +
                "}";

        final Response response = target(PATH_ACCOUNT)
                .request(MediaType.APPLICATION_JSON_TYPE)
                .accept(MediaType.APPLICATION_JSON_TYPE)
                .post(Entity.json(data));

        assertEquals(200, response.getStatus());
    }

    @Test
    public void shouldAddAccountUsingAccountObject() {

        Account account = new Account();
        account.setFirstName("John");
        account.setLastName("Doe");
        account.setPhoneNumber("555-456-9877");
        account.setBalance(3888.00);

        final Response response = target(PATH_ACCOUNT)
                .request(MediaType.APPLICATION_JSON_TYPE)
                .accept(MediaType.APPLICATION_JSON_TYPE)
                .post(Entity.json(account));

        assertEquals(200, response.getStatus());
    }

    @Test
    public void shouldUpdateFirstObject() {

        Account account = new Account();
        account.setFirstName("New first name");
        account.setLastName("New last name");
        account.setPhoneNumber("000000000");
        account.setBalance(0000.00);

        final Response response = target(PATH_ACCOUNT + "/10")
                .request()
                .put(Entity.entity(account, MediaType.APPLICATION_JSON));

        assertEquals(200, response.getStatus());
    }

    @Test
    public void shoudlDeleteAccount() {

        final String response = target(PATH_ACCOUNT + "/1")
                .request()
                .delete(String.class);

        Assert.assertEquals("{\"balance\":1000.0," +
                        "\"firstName\":\"Biniam\"," +
                        "\"id\":1," +
                        "\"lastName\":\"Asnake\"," +
                        "\"phoneNumber\":\"+4917621003292\"}",
                response);
    }

    @Test(expected = InternalServerErrorException.class)
    public void shouldThrowExceptionWhenInvalidAccountIsGiven() {

        final String response = target(PATH_ACCOUNT + "/999")
                .request()
                .get(String.class);

        Assert.assertTrue("Account with id 999 is invalid".equals(response));
    }
}
