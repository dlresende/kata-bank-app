package net.diegolemos.bankapp.account;

import net.diegolemos.bankapp.client.Client;
import net.diegolemos.bankapp.client.ClientRepository;

import javax.inject.Inject;
import java.util.Collection;

public class AccountService {
    private final AccountRepository accounts;
    private final ClientRepository clients;

    @Inject
    public AccountService(AccountRepository accounts, ClientRepository clients) {
        this.accounts = accounts;
        this.clients = clients;
    }

    public Account findBy(String username) {
        Client client = clients.withUsername(username);
        return accounts.forClient(client);
    }

    public Collection<Account> all() {
        return accounts.all();
    }

    public void save(Account account) {
        accounts.save(account);
    }
}