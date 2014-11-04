package net.diegolemos.bankapp.account;

import net.diegolemos.bankapp.client.Client;
import org.junit.Before;
import org.junit.Test;

import static net.diegolemos.bankapp.account.AccountBuilder.anAccount;
import static net.diegolemos.bankapp.client.ClientBuilder.aClient;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.core.IsCollectionContaining.hasItem;
import static org.junit.Assert.assertThat;

public class AccountRepositoryTest {

    private static final Client BOB = aClient().withUsername("bob").build();
    private static final Account BOB_ACCOUNT = anAccount().withHolder(BOB).build();

    private AccountRepository accounts;

    @Before
    public void init() {
        accounts = new AccountRepository();
    }

    @Test public void
    should_find_account_by_username() {
        accounts.createFor(BOB);

        Account bobAccount = accounts.forHolder(BOB);

        assertThat(bobAccount, equalTo(BOB_ACCOUNT));
    }

    @Test public void
    should_save_account() {
        accounts.save(BOB_ACCOUNT);

        assertThat(accounts.all(), hasItem(BOB_ACCOUNT));
    }
}