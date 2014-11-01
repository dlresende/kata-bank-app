package net.diegolemos.bankapp.client;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;
import java.util.Collection;

import static javax.ws.rs.core.Response.noContent;
import static javax.ws.rs.core.Response.ok;

@Path("client")
@Consumes("application/json;charset=utf-8")
@Produces("application/json;charset=utf-8")
public class ClientResource {

    private final ClientRepository clients;

    @Inject
    public ClientResource(ClientRepository clients) {
        this.clients = clients;
    }

    @PUT
    @Path("{username}")
    public Response save(@PathParam("username") String username, Client client) {
        clients.add(username, client);
        return noContent().build();
    }

    @GET
    @Path("{username}")
    public Response getByUsername(@PathParam("username") String username) {
        return ok(clients.withUsername(username)).build();
    }

    @GET
    public Response getAll() {
        Collection<Client> allClients = clients.all();
        return ok(serializeCollection(allClients)).build();
    }

    private GenericEntity<Collection<Client>> serializeCollection(final Collection<Client> collection) {
        return new GenericEntity<Collection<Client>>(collection) {};
    }
}
