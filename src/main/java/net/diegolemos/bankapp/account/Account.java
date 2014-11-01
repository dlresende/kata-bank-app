package net.diegolemos.bankapp.account;

import net.diegolemos.bankapp.client.Client;

public class Account {
    private Client holder;
    private double balance;

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Client getHolder() {
        return holder;
    }

    public void setHolder(Client holder) {
        this.holder = holder;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Account account = (Account) o;

        return Double.compare(account.balance, balance) == 0
                && !(holder != null ? !holder.equals(account.holder) : account.holder != null);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = holder != null ? holder.hashCode() : 0;
        temp = Double.doubleToLongBits(balance);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Account{" +
                "holder=" + holder +
                ", balance=" + balance +
                '}';
    }
}
