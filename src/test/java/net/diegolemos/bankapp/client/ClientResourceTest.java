package net.diegolemos.bankapp.client;

import net.diegolemos.AbstractHttpTest;
import org.junit.Test;

import javax.ws.rs.client.Entity;

import static javax.ws.rs.client.Entity.json;
import static net.diegolemos.bankapp.client.ClientBuilder.aClient;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class ClientResourceTest extends AbstractHttpTest {

    @Test
    public void should_create_a_new_client() {
        Client bob = aClient().withUsername("bob").build();

        resource("client/bob").request().put(json(bob));

        assertThat(resource("client/bob").request().get(Client.class), equalTo(bob));
    }
}
