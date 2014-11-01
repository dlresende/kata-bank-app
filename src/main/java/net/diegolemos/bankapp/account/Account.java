package net.diegolemos.bankapp.account;

public class Account {
    private double balance;

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Account account = (Account) o;

        return Double.compare(account.balance, balance) == 0;
    }

    @Override
    public int hashCode() {
        long temp = Double.doubleToLongBits(balance);
        return (int) (temp ^ (temp >>> 32));
    }

    @Override
    public String toString() {
        return "Account{" +
                "balance=" + balance +
                '}';
    }
}
