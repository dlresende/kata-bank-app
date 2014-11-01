package net.diegolemos.bankapp.account;

import net.diegolemos.bankapp.client.Client;
import org.junit.Test;

import static net.diegolemos.bankapp.account.AccountBuilder.anAccount;
import static net.diegolemos.bankapp.client.ClientBuilder.aClient;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class AccountRepositoryTest {

    private static final Client BOB = aClient().withUsername("bob").build();

    private AccountRepository accounts = new AccountRepository();

    @Test public void
    should_find_account_by_username() {
        accounts.createFor(BOB);

        Account bobAccount = accounts.forHolder(BOB);

        assertThat(bobAccount, equalTo(anAccount().withHolder(BOB).build()));
    }
}