package net.diegolemos.bankapp.transaction;

import org.junit.Test;

import static net.diegolemos.bankapp.transaction.Transaction.Action.DEPOSIT;
import static net.diegolemos.bankapp.transaction.Transaction.Action.WITHDRAW;
import static net.diegolemos.bankapp.transaction.TransactionBuilder.aDeposit;
import static net.diegolemos.bankapp.transaction.TransactionBuilder.aWithdraw;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class TransactionBuilderTest {

    @Test public void
    should_build_a_deposit_transaction() {
        Transaction deposit = aDeposit().of(100.0).build();

        assertThat(deposit.getAction(), is(DEPOSIT));
        assertThat(deposit.getAmount(), is(100.0));
    }

    @Test public void
    should_build_a_withdraw_transaction() {
        Transaction deposit = aWithdraw().of(10.0).build();

        assertThat(deposit.getAction(), is(WITHDRAW));
        assertThat(deposit.getAmount(), is(10.0));
    }
}
