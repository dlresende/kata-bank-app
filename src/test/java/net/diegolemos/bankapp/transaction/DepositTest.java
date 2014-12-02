package net.diegolemos.bankapp.transaction;

import org.junit.Test;

import static net.diegolemos.bankapp.transaction.Transaction.Type.DEPOSIT;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class DepositTest {

    @Test(expected = IllegalStateException.class) public void
    should_throw_an_exception_when_creating_a_deposit_with_negative_amount() {
        new Deposit(-5.0);
    }

    @Test public void
    transaction_type_should_be_DEPOSIT_for_every_deposit_instance() {
        assertThat(new Deposit(5.0).type(), is(DEPOSIT));
    }
}