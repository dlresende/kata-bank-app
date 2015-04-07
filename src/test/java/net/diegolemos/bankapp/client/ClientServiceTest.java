package net.diegolemos.bankapp.client;

import net.diegolemos.bankapp.AbstractHttpTest;
import org.junit.Test;

import javax.ws.rs.core.GenericType;
import java.util.Collection;

import static java.util.Arrays.asList;
import static net.diegolemos.bankapp.client.ClientBuilder.aClient;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

public class ClientServiceTest extends AbstractHttpTest {

    private static final Client BOB = aClient().withUsername("bob").build();
    private static final Client ALICE = aClient().withUsername("alice").build();

    private ClientRepository clients = injectMock(ClientRepository.class);
    private ClientService clientService = new ClientService(clients);

    @Test public void
    should_save_client() {
        clientService.save(BOB);

        verify(clients).add(BOB);
    }

    @Test public void
    should_find_client_by_username() {
        given(clients.withUsername("bob")).willReturn(BOB);

        Client client = clientService.findByUsername("bob");

        assertThat(client, equalTo(BOB));
    }

    @Test public void
    should_return_all_clients() {
        given(clients.all()).willReturn(asList(ALICE, BOB));

        Collection<Client> clients = clientService.all();

        assertThat(clients, hasItems(ALICE, BOB));
    }
}
