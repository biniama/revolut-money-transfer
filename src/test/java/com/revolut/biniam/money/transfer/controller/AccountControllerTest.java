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
 * @author Biniam Asnake
 */
public class AccountControllerTest extends JerseyTest {

    @Override
    protected Application configure() {
        return new ResourceConfig(AccountController.class);
    }

    @Test
    public void getAllAccounts() {
        final String response = target("account")
                .request(MediaType.APPLICATION_JSON_TYPE)
                .accept(MediaType.APPLICATION_JSON_TYPE)
                .get(String.class);

        assertEquals("[{\"balance\":1000.0,\"firstName\":\"Biniam\",\"id\":1,\"lastName\":\"Asnake\",\"phoneNumber\":\"+4917621003292\"}," +
                        "{\"balance\":8000.0,\"firstName\":\"Kidan\",\"id\":2,\"lastName\":\"Lakew\",\"phoneNumber\":\"+4917621003288\"}," +
                        "{\"balance\":50000.0,\"firstName\":\"Hasset\",\"id\":3,\"lastName\":\"Biniam\",\"phoneNumber\":\"+491111111111\"}," +
                        "{\"balance\":70000.0,\"firstName\":\"Naod\",\"id\":4,\"lastName\":\"Biniam\",\"phoneNumber\":\"+4922222222222\"}]",
                response);
    }

    @Test
    public void shouldAddAccount() {

        String data = "{" +
            "\"firstName\":\"Kidan\", " +
                "\"lastName\": \"Lakew\", " +
                "\"phoneNumber\": \"+49333333333\", " +
                "\"balance\": 3000.00 " +
                "}";

        final Response response = target("account")
                .request(MediaType.APPLICATION_JSON_TYPE)
                .accept(MediaType.APPLICATION_JSON_TYPE)
                .post(Entity.json(data));

        assertEquals(response.getStatus(), 200);
    }

    @Test
    public void shouldAddAccountUsingAccountObject() {

        Account account = new Account();
        account.setFirstName("John");
        account.setLastName("Doe");
        account.setPhoneNumber("555-456-9877");
        account.setBalance(3888.00);

        final Response response = target("account")
                .request(MediaType.APPLICATION_JSON_TYPE)
                .accept(MediaType.APPLICATION_JSON_TYPE)
                .post(Entity.json(account));

        assertEquals(response.getStatus(), 200);
    }

    @Test
    public void shouldUpdateFirstObject() {

        Account account = new Account();
        account.setFirstName("New first name");
        account.setLastName("New last name");
        account.setPhoneNumber("000000000");
        account.setBalance(0000.00);

        Response response = target("/account/10").request()
                .put(Entity.entity(account, MediaType.APPLICATION_JSON));

        Assert.assertEquals(response.getStatus(), 200);
    }

    @Test(expected = InternalServerErrorException.class)
    public void shouldThrowAccountNotFoundException() {
        String response = target("account/999").request().get(String.class);
        Assert.assertTrue("Account with id 999 is invalid".equals(response));
    }
}
