package net.diegolemos.bankapp.transaction;

import java.util.LinkedList;
import java.util.List;

import static net.diegolemos.bankapp.transaction.Transaction.Action.DEPOSIT;
import static net.diegolemos.bankapp.transaction.Transaction.Action.WITHDRAW;

public class Transactions {
    private List<Transaction> transactions = new LinkedList<>();

    public void add(Transaction transaction) {
        transactions.add(transaction);
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Transactions that = (Transactions) o;

        return !(transactions != null ? !transactions.equals(that.transactions) : that.transactions != null);
    }

    @Override
    public int hashCode() {
        return transactions != null ? transactions.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Transactions{" +
                "transactions=" + transactions +
                '}';
    }

    public double balance() {
        double balance = 0.0;

        for(Transaction transaction : transactions) {
            if(transaction.getAction() == DEPOSIT) {
                balance += transaction.getAmount();
            }

            else if(transaction.getAction() == WITHDRAW) {
                balance -= transaction.getAmount();
            }
        }

        return balance;
    }
}
