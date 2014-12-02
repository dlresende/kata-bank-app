package net.diegolemos.bankapp.transaction;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class Transaction {
    @JsonProperty
    private Type type;

    @JsonProperty
    private double amount;

    @JsonProperty
    private Date date = now();

    // Required by Jackson
    private Transaction() {
    }

    public Transaction(Type type, double amount) {
        this.type = type;
        this.amount = amount;
    }

    public Type type() {
        return type;
    }

    public double amount() {
        return amount;
    }

    public Date date() {
        return date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Transaction that = (Transaction) o;

        if (Double.compare(that.amount, amount) != 0) return false;
        if (type != that.type) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = type != null ? type.hashCode() : 0;
        temp = Double.doubleToLongBits(amount);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (date != null ? date.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "type=" + type +
                ", amount=" + amount +
                ", date=" + date +
                '}';
    }

    private static Date now() {
        return new Date();
    }

    public enum Type {WITHDRAW, DEPOSIT}
}
