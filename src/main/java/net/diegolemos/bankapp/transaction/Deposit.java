package net.diegolemos.bankapp.transaction;

import static net.diegolemos.bankapp.transaction.Transaction.Action.DEPOSIT;

public class Deposit extends Transaction {

    public Deposit() {
        super.setAction(DEPOSIT);
    }

    @Override
    public void setAction(Action action) {
        // Do nothing
    }
}
