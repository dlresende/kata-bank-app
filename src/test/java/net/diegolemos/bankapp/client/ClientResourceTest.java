package net.diegolemos.bankapp.client;

import net.diegolemos.bankapp.AbstractHttpTest;
import net.diegolemos.bankapp.account.AccountRepository;
import org.junit.Test;

import javax.ws.rs.client.WebTarget;

import static javax.ws.rs.client.Entity.json;
import static net.diegolemos.bankapp.client.ClientBuilder.aClient;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

public class ClientResourceTest extends AbstractHttpTest {

    private static final Client BOB = aClient().withUsername("bob").build();

    private AccountRepository accounts = injectMock(AccountRepository.class);
    private ClientRepository clients = injectMock(ClientRepository.class);
    private WebTarget clientResource = resource("client");

    @Test public void
    should_create_a_new_client() {
        clientResource.path("bob").request().put(json(BOB));

        verify(clients).add("bob", BOB);
    }

    @Test public void
    should_get_client_by_username() {
        given(clients.withUsername("bob")).willReturn(BOB);

        Client client = clientResource.path("bob").request().get(Client.class);

        assertThat(client, equalTo(BOB));
    }

    @Test public void
    should_create_an_account_with_balance_0_for_new_users() {
        clientResource.path("bob").request().put(json(BOB));

        verify(accounts).createFor(BOB);
    }
}
