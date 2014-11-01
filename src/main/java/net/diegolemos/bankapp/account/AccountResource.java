package net.diegolemos.bankapp.account;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.Response.ok;

@Path("account")
@Consumes("application/json;charset=utf-8")
@Produces("application/json;charset=utf-8")
public class AccountResource {

    private final AccountRepository accounts;

    @Inject
    public AccountResource(AccountRepository accounts) {
        this.accounts = accounts;
    }

    @GET
    @Path("{username}")
    public Response getByUsername(@PathParam("username") String username) {
        Account account = accounts.findForClient(username);
        return ok(account).build();
    }
}
