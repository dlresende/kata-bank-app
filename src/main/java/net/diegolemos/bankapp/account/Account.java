package net.diegolemos.bankapp.account;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import net.diegolemos.bankapp.client.Client;
import net.diegolemos.bankapp.transaction.Deposit;
import net.diegolemos.bankapp.transaction.Transactions;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Account {
    @JsonProperty
    private Client holder;

    @JsonProperty
    private Transactions transactions = new Transactions();

    // Required by Jackson
    private Account() {}

    public Account(Client holder) {
        this.holder = holder;
    }

    public Client holder() {
        return holder;
    }

    @JsonProperty("balance")
    public double balance() {
        return transactions.balance();
    }

    public void deposit(double amount) {
        transactions.add(new Deposit(amount));
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
                ", balance=" + balance() +
                ", transactions=" + transactions +
                '}';
    }
}
