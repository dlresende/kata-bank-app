package net.diegolemos.bankapp.account;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

public class AccountRepositoryTest {

    private static final Account EMPTY_ACCOUNT = new Account();

    private AccountRepository accounts = new AccountRepository();

    @Test public void
    should_find_account_by_username() {
        accounts.createFor("bob");

        Account account = accounts.findForClient("bob");

        assertThat(account, equalTo(EMPTY_ACCOUNT));
    }
}