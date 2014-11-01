package net.diegolemos.bankapp.account;

import net.diegolemos.bankapp.client.Client;
import org.junit.Test;

import static net.diegolemos.bankapp.client.ClientBuilder.aClient;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class AccountRepositoryTest {

    private static final Account EMPTY_ACCOUNT = new Account();
    private static final Client BOB = aClient().withUsername("bob").build();

    private AccountRepository accounts = new AccountRepository();

    @Test public void
    should_find_account_by_username() {
        accounts.createFor(BOB);

        Account account = accounts.forHolder(BOB);

        assertThat(account, equalTo(EMPTY_ACCOUNT));
    }
}