package net.diegolemos.bankapp.account;

import net.diegolemos.bankapp.client.Client;
import org.junit.Before;
import org.junit.Test;

import static net.diegolemos.bankapp.client.ClientBuilder.aClient;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class AccountTest {

    private static final Client BOB = aClient().withUsername("bob").build();

    private Account account;

    @Before
    public void initialize() {
        account = new Account(BOB);
    }

    @Test public void
    should_calculate_balance_for_transactions() {
        account.deposit(10.0);
        account.deposit(20.0);

        assertThat(account.balance(), is(30.0));
    }

    @Test public void
    should_deposit_money_into_account() {
        account.deposit(10.0);

        assertThat(account.balance(), is(10.0));
    }
}