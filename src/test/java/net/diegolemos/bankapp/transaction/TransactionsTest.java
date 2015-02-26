package net.diegolemos.bankapp.transaction;

import org.junit.Test;

import static net.diegolemos.bankapp.transaction.Transaction.deposit;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class TransactionsTest {

    @Test public void
    should_compute_transactions_balance() {
        Transactions transactions = new Transactions();
        transactions.add(deposit(10.0));
        transactions.add(deposit(10.0));

        double balance = transactions.balance();

        assertThat(balance, is(20.0));
    }

    @Test public void
    balance_should_be_zero_when_there_is_no_transactions() {
        Transactions transactions = new Transactions();

        double balance = transactions.balance();

        assertThat(balance, is(0.0));
    }
}