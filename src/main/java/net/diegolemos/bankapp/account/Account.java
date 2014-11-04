package net.diegolemos.bankapp.account;

import net.diegolemos.bankapp.client.Client;
import net.diegolemos.bankapp.transaction.Transaction;
import net.diegolemos.bankapp.transaction.Transactions;
import org.codehaus.jackson.annotate.JsonProperty;

public class Account {
    private Client holder;
    private Transactions transactions = new Transactions();

    @JsonProperty("balance")
    public double getBalance() {
        return transactions.balance();
    }

    public Client getHolder() {
        return holder;
    }

    public void setHolder(Client holder) {
        this.holder = holder;
    }

    public Transactions getTransactions() {
        return transactions;
    }

    public void setTransactions(Transactions transactions) {
        this.transactions = transactions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Account account = (Account) o;

        return !(holder != null ? !holder.equals(account.holder) : account.holder != null);
    }

    @Override
    public int hashCode() {
        return holder != null ? holder.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Account{" +
                "holder=" + holder +
                ", balance=" + getBalance() +
                ", transactions=" + transactions +
                '}';
    }

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }
}
