package net.diegolemos.bankapp.transaction;

public class TransactionBuilder {

    private double amount;
    private Transaction transaction;

    public static TransactionBuilder aDeposit() {
        TransactionBuilder transactionBuilder = new TransactionBuilder();
        transactionBuilder.transaction = new Deposit();
        return transactionBuilder;
    }

    public TransactionBuilder of(double amount) {
        this.amount = amount;
        return this;
    }

    public Transaction build() {
        transaction.setAmount(amount);
        return transaction;
    }
}
