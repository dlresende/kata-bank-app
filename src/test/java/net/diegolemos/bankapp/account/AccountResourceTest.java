package net.diegolemos.bankapp.account;

import net.diegolemos.bankapp.AbstractHttpTest;
import net.diegolemos.bankapp.client.Client;
import net.diegolemos.bankapp.client.ClientRepository;
import org.junit.Test;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import java.util.Collection;

import static java.util.Arrays.asList;
import static javax.ws.rs.client.Entity.json;
import static net.diegolemos.bankapp.account.AccountBuilder.anAccount;
import static net.diegolemos.bankapp.client.ClientBuilder.aClient;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

public class AccountResourceTest extends AbstractHttpTest {

    private static final Client BOB = aClient().withUsername("bob").build();
    private static final Account EMPTY_ACCOUNT = new Account();
    private static final Account BOB_ACCOUNT = anAccount().withHolder(BOB).build();

    private ClientRepository clients = injectMock(ClientRepository.class);
    private AccountRepository accounts = injectMock(AccountRepository.class);
    private WebTarget accountResource = resource("account");

    @Test public void
    should_get_the_account_balance_for_a_given_client() {
        given(clients.withUsername("bob")).willReturn(BOB);
        given(accounts.forHolder(BOB)).willReturn(BOB_ACCOUNT);

        Account bobAccount = accountResource.path("bob").request().get(Account.class);

        assertThat(bobAccount.balance(), is(0.0));
    }

    @Test public void
    should_create_a_new_account_for_a_given_client() {
        accountResource.request().put(json(BOB_ACCOUNT));

        verify(accounts).save(BOB_ACCOUNT);
    }

    @Test public void
    should_get_all_accounts() {
        given(accounts.all()).willReturn(asList(BOB_ACCOUNT, EMPTY_ACCOUNT));

        Collection<Account> accounts = accountResource.request().get(new GenericType<Collection<Account>>() {});

        assertThat(accounts, hasItems(BOB_ACCOUNT, EMPTY_ACCOUNT));
    }

    @Test public void
    should_add_new_transaction_for_account() {
        Account account = new Account();
        account.deposit(10);

        accountResource.request().put(json(account));

        verify(accounts).save(account);
    }
}
