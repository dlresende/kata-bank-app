package net.diegolemos.bankapp.steps.account;

public interface Account {
    void createFor(net.diegolemos.bankapp.client.Client client);

    void deposit(double balance);

    void checkBalance(double expectedBalance);
}
