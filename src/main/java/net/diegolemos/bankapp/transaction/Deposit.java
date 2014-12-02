package net.diegolemos.bankapp.transaction;

import static net.diegolemos.bankapp.transaction.Transaction.Action.DEPOSIT;

public class Deposit extends Transaction {

    public Deposit(double amount) {
        super(DEPOSIT, amount);

        if(isNegative(amount)) {
            throw new IllegalStateException("Values for deposits must be positive. Invalid value: " + amount);
        }
    }

    private boolean isNegative(double amount) {
        return amount < 0;
    }
}
