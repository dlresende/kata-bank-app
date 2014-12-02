package net.diegolemos.bankapp.account;

import net.diegolemos.bankapp.client.Client;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


public class AccountRepository {

    private final Map<Client, Account> allAccounts = new HashMap<>();

    public Account forClient(Client client) {
        return allAccounts.get(client);
    }

    public void createFor(Client client) {
        Account account = new Account(client);
        allAccounts.put(client, account);
    }

    public Collection<Account> all() {
        return allAccounts.values();
    }

    public void save(Account account) {
        allAccounts.put(account.holder(), account);
    }
}
