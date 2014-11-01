package net.diegolemos.bankapp.account;

import java.util.HashMap;
import java.util.Map;

public class AccountRepository {

    private final Map<String, Account> allAccounts = new HashMap<>();

    public Account findForClient(String username) {
        return allAccounts.get(username);
    }

    public void createFor(String username) {
        allAccounts.put(username, new Account());
    }
}
