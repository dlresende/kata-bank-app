package net.diegolemos.bankapp.account;

import net.diegolemos.bankapp.client.Client;

import java.util.HashMap;
import java.util.Map;

public class AccountRepository {

    private final Map<Client, Account> allAccounts = new HashMap<>();

    public Account forHolder(Client client) {
        return allAccounts.get(client);
    }

    public void createFor(Client client) {
        Account account = new Account();
        account.setHolder(client);
        allAccounts.put(client, account);
    }
}