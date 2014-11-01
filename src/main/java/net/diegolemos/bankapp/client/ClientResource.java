package net.diegolemos.bankapp.client;

import net.diegolemos.bankapp.account.AccountRepository;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.Response.noContent;
import static javax.ws.rs.core.Response.ok;

@Path("client")
@Consumes("application/json;charset=utf-8")
@Produces("application/json;charset=utf-8")
public class ClientResource {

    private final AccountRepository accounts;
    private final ClientRepository clients;

    @Inject
    public ClientResource(AccountRepository accounts, ClientRepository clients) {
        this.accounts = accounts;
        this.clients = clients;
    }

    @PUT
    @Path("{username}")
    public Response save(@PathParam("username") String username, Client client) {
        clients.add(username, client);
        accounts.createFor(username);
        return noContent().build();
    }

    @GET
    @Path("{username}")
    public Response getByUsername(@PathParam("username") String username) {
        return ok(clients.get(username)).build();
    }
}
