package net.diegolemos.bankapp.account;

import net.diegolemos.bankapp.client.Client;
import org.junit.Test;

import static net.diegolemos.bankapp.account.AccountBuilder.anAccount;
import static net.diegolemos.bankapp.client.ClientBuilder.aClient;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class AccountBuilderTest {

    private static final Client ALICE = aClient().withUsername("alice").build();

    @Test public void
    should_create_an_account_with_holder() {
        Account aliceAccount = new Account(ALICE);

        assertThat(aliceAccount, equalTo(anAccount().withHolder(ALICE).build()));
    }
}
