package net.diegolemos.bankapp.client;

import org.junit.Test;

import static net.diegolemos.bankapp.client.ClientBuilder.aClient;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class ClientBuilderTest {

    @Test public void
    should_build_client_with_username() {
        Client bob = new Client();
        bob.setUsername("bob");

        assertThat(aClient().withUsername("bob").build(), equalTo(bob));
    }
}
