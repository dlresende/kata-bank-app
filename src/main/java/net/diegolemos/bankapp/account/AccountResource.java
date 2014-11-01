package net.diegolemos.bankapp.account;

import net.diegolemos.bankapp.client.Client;
import net.diegolemos.bankapp.client.ClientRepository;

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

    private final AccountRepository accounts;
    private final ClientRepository clients;

    @Inject
    public AccountResource(AccountRepository accounts, ClientRepository clients) {
        this.accounts = accounts;
        this.clients = clients;
    }

    @GET
    @Path("{username}")
    public Response getByUsername(@PathParam("username") String username) {
        Client client = clients.withUsername(username);
        Account account = accounts.forHolder(client);
        return ok(account).build();
    }

    @PUT
    public Response createAccount(Client client) {
        accounts.createFor(client);
        return noContent().build();
    }

    @GET
    public Response getAll() {
        Collection<Account> allAccounts = accounts.all();
        return ok(serializeCollection(allAccounts)).build();
    }

    private GenericEntity<Collection<Account>> serializeCollection(final Collection<Account> collection) {
        return new GenericEntity<Collection<Account>>(collection) {};
    }
}
