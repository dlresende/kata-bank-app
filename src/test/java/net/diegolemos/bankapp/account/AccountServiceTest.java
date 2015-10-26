package net.diegolemos.bankapp.account;

import net.diegolemos.bankapp.client.Client;
import net.diegolemos.bankapp.client.ClientRepository;
import org.junit.Before;
import org.junit.Test;

import static net.diegolemos.bankapp.client.ClientBuilder.aClient;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class AccountServiceTest {

    private static final Client BOB = aClient().withUsername("bob").build();
    private static final Account BOB_ACCOUNT = new Account(BOB);

    private ClientRepository clients;
    private AccountRepository accounts;
    private AccountService accountService;

    @Before
    public void setUp() {
        clients = mock(ClientRepository.class);
        accounts = mock(AccountRepository.class);
        accountService = new AccountService(accounts, clients);
    }

    @Test public void
    should_get_the_account_for_a_given_client() {
        given(clients.withUsername("bob")).willReturn(BOB);
        given(accounts.forClient(BOB)).willReturn(BOB_ACCOUNT);

        Account bobAccount = accountService.findBy("bob");

        assertThat(bobAccount.balance(), is(0.0));
    }
}
