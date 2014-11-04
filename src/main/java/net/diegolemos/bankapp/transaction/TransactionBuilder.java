package net.diegolemos.bankapp.transaction;

import static net.diegolemos.bankapp.transaction.Transaction.Action;
import static net.diegolemos.bankapp.transaction.Transaction.Action.DEPOSIT;

public class TransactionBuilder {

    private double amount;
    private Action action = DEPOSIT;

    public static TransactionBuilder aDeposit() {
        return new TransactionBuilder();
    }

    public TransactionBuilder of(double amount) {
        this.amount = amount;
        return this;
    }

    public Transaction build() {
        Transaction transaction = new Transaction();
        transaction.setAction(action);
        transaction.setAmount(amount);
        return transaction;
    }
}
