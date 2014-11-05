package net.diegolemos.bankapp.transaction;

import static net.diegolemos.bankapp.transaction.Transaction.Action.WITHDRAW;

public class Withdraw extends Transaction {

    public Withdraw() {
        super.setAction(WITHDRAW);
    }

    @Override
    public void setAction(Action action) {
        // Do nothing
    }
}
