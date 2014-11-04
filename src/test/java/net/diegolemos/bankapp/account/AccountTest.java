package net.diegolemos.bankapp.account;

import org.junit.Test;

import static net.diegolemos.bankapp.account.AccountBuilder.anAccount;
import static net.diegolemos.bankapp.transaction.TransactionBuilder.aDeposit;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

public class AccountTest {

    @Test public void
    should_credit_user_balance_when_on_deposit() {
        Account account = anAccount().build();

        account.addTransaction(aDeposit().of(10.0).build());

        assertThat(account.getBalance(), equalTo(10.0));
    }
}