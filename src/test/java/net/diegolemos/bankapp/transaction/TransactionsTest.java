package net.diegolemos.bankapp.transaction;

import org.junit.Test;

import static net.diegolemos.bankapp.transaction.TransactionBuilder.aDeposit;
import static net.diegolemos.bankapp.transaction.TransactionBuilder.aWithdraw;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

public class TransactionsTest {

    @Test public void
    should_compute_transactions_balance_for_deposit() {
        Transactions transactions = new Transactions();
        transactions.add(aDeposit().of(10.0).build());
        transactions.add(aDeposit().of(10.0).build());

        double balance = transactions.balance();

        assertThat(balance, equalTo(20.0));
    }

    @Test public void
    should_compute_transactions_balance_for_withdraw() {
        Transactions transactions = new Transactions();
        transactions.add(aWithdraw().of(10.0).build());

        double balance = transactions.balance();

        assertThat(balance, equalTo(-10.0));
    }

    @Test public void
    balance_should_be_zero_when_there_is_no_transactions() {
        Transactions transactions = new Transactions();

        double balance = transactions.balance();

        assertThat(balance, equalTo(0.0));
    }
}