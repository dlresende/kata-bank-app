package net.diegolemos.bankapp.account;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;
import java.util.Collection;

import static javax.ws.rs.core.Response.noContent;
import static javax.ws.rs.core.Response.ok;

@Path("account")
@Consumes("application/json;charset=utf-8")
@Produces("application/json;charset=utf-8")
public class AccountResource {

    private final AccountService accountService;

    @Inject
    public AccountResource(AccountService accountService) {
        this.accountService = accountService;
    }

    @GET
    @Path("{username}")
    public Response getByUsername(@PathParam("username") String username) {
        Account account = accountService.findBy(username);
        return ok(account).build();
    }

    @GET
    public Response getAll() {
        return ok(serializeCollection(accountService.all())).build();
    }

    @PUT
    public Response save(Account account) {
        accountService.save(account);
        return noContent().build();
    }

    private GenericEntity<Collection<Account>> serializeCollection(final Collection<Account> collection) {
        return new GenericEntity<Collection<Account>>(collection) {};
    }
}
