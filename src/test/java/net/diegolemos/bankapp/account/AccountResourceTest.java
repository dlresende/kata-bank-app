package net.diegolemos.bankapp.account;

import net.diegolemos.bankapp.AbstractHttpTest;
import net.diegolemos.bankapp.client.Client;
import net.diegolemos.bankapp.client.ClientRepository;
import org.junit.Test;
import org.mockito.Mockito;

import javax.ws.rs.client.WebTarget;

import static javax.ws.rs.client.Entity.json;
import static net.diegolemos.bankapp.client.ClientBuilder.aClient;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

public class AccountResourceTest extends AbstractHttpTest {

    private static final Client BOB = aClient().withUsername("bob").build();
    private static final Account EMPTY_ACCOUNT = new Account();

    private ClientRepository clients = injectMock(ClientRepository.class);
    private AccountRepository accounts = injectMock(AccountRepository.class);
    private WebTarget accountResource = resource("account");

    @Test public void
    should_get_the_account_balance_for_a_given_user() {
        given(clients.withUsername("bob")).willReturn(BOB);
        given(accounts.forHolder(BOB)).willReturn(EMPTY_ACCOUNT);

        Account bobAccount = accountResource.path("bob").request().get(Account.class);

        assertThat(bobAccount.getBalance(), equalTo(0.0));
    }

    @Test public void
    should_create_a_new_account_for_a_given_client() {
        accountResource.request().put(json(BOB));

        verify(accounts).createFor(BOB);
    }
}
