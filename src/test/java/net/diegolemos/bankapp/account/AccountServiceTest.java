package net.diegolemos.bankapp.account;

import net.diegolemos.bankapp.AbstractHttpTest;
import net.diegolemos.bankapp.client.Client;
import net.diegolemos.bankapp.client.ClientRepository;
import org.junit.Test;

import java.util.Collection;

import static java.util.Arrays.asList;
import static net.diegolemos.bankapp.client.ClientBuilder.aClient;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

public class AccountServiceTest extends AbstractHttpTest {

    private static final Client ALICE = aClient().withUsername("alice").build();
    private static final Client BOB = aClient().withUsername("bob").build();
    private static final Account ALICE_ACCOUNT = new Account(ALICE);
    private static final Account BOB_ACCOUNT = new Account(BOB);

    private ClientRepository clients = injectMock(ClientRepository.class);
    private AccountRepository accounts = injectMock(AccountRepository.class);
    private AccountService accountService = new AccountService(accounts, clients);

    @Test public void
    should_get_the_account_for_a_given_client() {
        given(clients.withUsername("bob")).willReturn(BOB);
        given(accounts.forClient(BOB)).willReturn(BOB_ACCOUNT);

        Account bobAccount = accountService.findBy("bob");

        assertThat(bobAccount.balance(), is(0.0));
    }

    @Test public void
    should_create_a_new_account_for_a_given_client() {
        accountService.save(BOB_ACCOUNT);

        verify(accounts).save(BOB_ACCOUNT);
    }

    @Test public void
    should_get_all_accounts() {
        given(accounts.all()).willReturn(asList(BOB_ACCOUNT, ALICE_ACCOUNT));

        Collection<Account> accounts = accountService.all();

        assertThat(accounts, hasItems(BOB_ACCOUNT, ALICE_ACCOUNT));
    }
}
