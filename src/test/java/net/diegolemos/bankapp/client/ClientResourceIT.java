package net.diegolemos.bankapp.client;

import net.diegolemos.bankapp.HttpServerIT;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import java.util.Collection;

import static javax.ws.rs.client.Entity.json;
import static net.diegolemos.bankapp.client.ClientBuilder.aClient;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

public class ClientResourceIT extends HttpServerIT {

    private static final Client BOB = aClient().withUsername("bob").build();
    private static final Client ALICE = aClient().withUsername("alice").build();

    private WebTarget clientResource;

    @Before
    public void setUp() {
        clientResource = resource("client");
    }

    @Test public void
    should_save_client() {
        clientResource.path("bob").request().put(json(BOB));

        assertThat(clientResource.path("bob").request().get(new GenericType<Client>() {}), is(equalTo(BOB)));
    }

    @Test public void
    should_get_client_by_name() {
        clientResource.path("bob").request().put(json(BOB));

        Client client = clientResource.path("bob").request().get(Client.class);

        assertThat(client, equalTo(BOB));
    }

    @Test public void
    should_get_all_clients() {
        clientResource.path("alice").request().put(json(ALICE));
        clientResource.path("bob").request().put(json(BOB));

        Collection<Client> clients = clientResource.request().get(new GenericType<Collection<Client>>() {});

        assertThat(clients, hasItems(ALICE, BOB));
    }
}
