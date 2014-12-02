package net.diegolemos.bankapp.account;

import net.diegolemos.bankapp.client.Client;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static net.diegolemos.bankapp.account.AccountBuilder.anAccount;

public class AccountRepository {

    private final Map<Client, Account> allAccounts = new HashMap<>();

    public Account forHolder(Client client) {
        return allAccounts.get(client);
    }

    public void createFor(Client client) {
        Account account = anAccount().withHolder(client).build();
        allAccounts.put(client, account);
    }

    public Collection<Account> all() {
        return allAccounts.values();
    }

    public void save(Account account) {
        allAccounts.put(account.holder(), account);
    }
}
