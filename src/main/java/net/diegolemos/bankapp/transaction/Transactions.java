package net.diegolemos.bankapp.transaction;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.LinkedList;
import java.util.List;

import static net.diegolemos.bankapp.transaction.Transaction.Type.DEPOSIT;
import static net.diegolemos.bankapp.transaction.Transaction.Type.WITHDRAW;

public class Transactions {
    @JsonProperty
    private List<Transaction> transactions = new LinkedList<>();

    public void add(Transaction transaction) {
        transactions.add(transaction);
    }

    public double balance() {
        double balance = 0.0;

        for(Transaction transaction : transactions) {
            if(transaction.type() == DEPOSIT) {
                balance += transaction.amount();
            }

            else if(transaction.type() == WITHDRAW) {
                balance -= transaction.amount();
            }
        }

        return balance;
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
}
