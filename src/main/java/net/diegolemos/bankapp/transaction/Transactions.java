package net.diegolemos.bankapp.transaction;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.LinkedList;
import java.util.List;

public class Transactions {

    @JsonProperty
    private List<Transaction> transactions = new LinkedList<>();

    public void add(Transaction transaction) {
        transactions.add(transaction);
    }

    public double balance() {
        return transactions.stream().mapToDouble(Transaction::amount).sum();
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
