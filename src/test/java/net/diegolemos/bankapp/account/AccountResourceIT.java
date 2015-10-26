package net.diegolemos.bankapp.account;

import net.diegolemos.bankapp.HttpServerIT;
import net.diegolemos.bankapp.client.Client;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import java.util.Collection;

import static javax.ws.rs.client.Entity.json;
import static net.diegolemos.bankapp.client.ClientBuilder.aClient;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

public class AccountResourceIT extends HttpServerIT {

    private static final Client ALICE = aClient().withUsername("alice").build();
    private static final Client BOB = aClient().withUsername("bob").build();
    private static final Account ALICE_ACCOUNT = new Account(ALICE);
    private static final Account BOB_ACCOUNT = new Account(BOB);

    private WebTarget clientResource;
    private WebTarget accountResource;

    @Before
    public void setUp() {
        clientResource = resource("client");
        accountResource = resource("account");
        clientResource.path("bob").request().put(json(BOB));
    }

    @Test public void
    should_get_account_of_a_specific_client() {
        accountResource.request().put(json(BOB_ACCOUNT));

        Account bobAccount = accountResource.path("bob").request().get(Account.class);

        assertThat(bobAccount.balance(), is(0.0));
    }

    @Test public void
    should_create_a_new_account_for_a_given_client() {
        accountResource.request().put(json(BOB_ACCOUNT));

        assertThat(accountResource.path("bob").request().get(Account.class), is(equalTo(BOB_ACCOUNT)));
    }

    @Test public void
    should_get_all_accounts() {
        accountResource.request().put(json(BOB_ACCOUNT));
        clientResource.path("alice").request().put(json(ALICE));
        accountResource.request().put(json(ALICE_ACCOUNT));

        Collection<Account> accounts = accountResource.request().get(new GenericType<Collection<Account>>() {});

        assertThat(accounts, hasItems(BOB_ACCOUNT, ALICE_ACCOUNT));
    }

    @Test public void
    should_update_account() {
        accountResource.request().put(json(BOB_ACCOUNT)).readEntity(Account.class);
        Account bobAccount = accountResource.path("bob").request().get(Account.class);
        bobAccount.deposit(10);

        accountResource.request().put(json(bobAccount));

        assertThat(accountResource.path("bob").request().get(Account.class).balance(), is(10D));
    }
}
