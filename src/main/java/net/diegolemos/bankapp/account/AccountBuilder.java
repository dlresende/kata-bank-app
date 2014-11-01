package net.diegolemos.bankapp.account;

import net.diegolemos.bankapp.client.Client;

public class AccountBuilder {

    private Client holder;

    public static AccountBuilder anAccount() {
        return new AccountBuilder();
    }

    public AccountBuilder withHolder(Client holder) {
        this.holder = holder;
        return this;
    }

    public Account build() {
        Account account = new Account();
        account.setHolder(holder);
        return account;
    }
}
