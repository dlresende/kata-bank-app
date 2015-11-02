package net.diegolemos.bankapp.steps.account;

import net.diegolemos.bankapp.account.AccountRepository;
import net.diegolemos.bankapp.account.AccountService;
import net.diegolemos.bankapp.client.ClientRepository;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

public class AccountUnit implements Account {
    private AccountService accountService;
    private net.diegolemos.bankapp.account.Account clientAccount;

    public AccountUnit() {
        ClientRepository clients = mock(ClientRepository.class);
        AccountRepository accounts = mock(AccountRepository.class);
        accountService = new AccountService(accounts, clients);
    }

    @Override
    public void createFor(net.diegolemos.bankapp.client.Client client) {
        clientAccount = new net.diegolemos.bankapp.account.Account(client);
        accountService.save(clientAccount);
    }

    @Override
    public void deposit(double balance) {
        clientAccount.deposit(balance);
    }

    @Override
    public void checkBalance(double expectedBalance) {
        assertThat(clientAccount.balance(), is(expectedBalance));
    }
}