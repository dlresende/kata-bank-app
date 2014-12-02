package net.diegolemos.bankapp.transaction;

import static net.diegolemos.bankapp.transaction.Transaction.Action.DEPOSIT;

public class Deposit extends Transaction {

    public Deposit() {
        super.setAction(DEPOSIT);
    }

    public Deposit(double amount) {
        this();
        setAmount(amount);
    }

    @Override
    public void setAction(Action action) {
        // Do nothing
    }

    @Override
    public void setAmount(double amount) {
        if(amount < 0) {
            throw new IllegalStateException("Values for deposits must be positive. Invalid value: " + amount);
        }

        super.setAmount(amount);
    }
}
