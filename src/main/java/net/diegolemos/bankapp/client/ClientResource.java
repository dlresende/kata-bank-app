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

    private final ClientService clientService;

    @Inject
    public ClientResource(ClientService clientService) {
        this.clientService = clientService;
    }

    @PUT
    @Path("{username}")
    public Response save(@PathParam("username") String username, Client client) {
        clientService.save(client);
        return noContent().build();
    }

    @GET
    @Path("{username}")
    public Response getByUsername(@PathParam("username") String username) {
        return ok(clientService.findByUsername(username)).build();
    }

    @GET
    public Response getAll() {
        Collection<Client> allClients = clientService.all();
        return ok(serializeCollection(allClients)).build();
    }

    private GenericEntity<Collection<Client>> serializeCollection(final Collection<Client> collection) {
        return new GenericEntity<Collection<Client>>(collection) {};
    }
}
