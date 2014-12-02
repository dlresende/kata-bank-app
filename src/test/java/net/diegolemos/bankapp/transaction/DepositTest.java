package net.diegolemos.bankapp.transaction;

import org.junit.Test;

import static net.diegolemos.bankapp.transaction.Transaction.Action.DEPOSIT;
import static net.diegolemos.bankapp.transaction.Transaction.Action.WITHDRAW;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class DepositTest {

    @Test(expected = IllegalStateException.class) public void
    should_throw_an_exception_when_creating_a_deposit_with_negative_amount() {
        new Deposit(-5.0);
    }

    @Test public void
    should_not_update_transaction_type_when_the_new_type_is_set_to_withdraw() {
        Deposit deposit = new Deposit();
        deposit.setAction(WITHDRAW);

        assertThat(deposit.getAction(), is(DEPOSIT));
    }

    @Test public void
    transaction_type_should_be_DEPOSIT_for_every_deposit_instance() {
        assertThat(new Deposit().getAction(), is(DEPOSIT));
        assertThat(new Deposit(5.0).getAction(), is(DEPOSIT));
    }
}