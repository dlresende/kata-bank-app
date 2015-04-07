package net.diegolemos.bankapp.steps;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.diegolemos.bankapp.account.Account;
import net.diegolemos.bankapp.account.AccountRepository;
import net.diegolemos.bankapp.account.AccountService;
import net.diegolemos.bankapp.client.Client;
import net.diegolemos.bankapp.client.ClientRepository;

import static net.diegolemos.bankapp.client.ClientBuilder.aClient;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

public class AccountStepdefs {

    private ClientRepository clients;
    private AccountRepository accounts;
    private AccountService accountService;
    private Account clientAccount;

    @Before
    public void setUp() {
        clients = mock(ClientRepository.class);
        accounts = mock(AccountRepository.class);
        accountService = new AccountService(accounts, clients);
    }

    private Client client;

    @Given("^an existing client named \"([^\"]*)\"$")
    public void an_existing_client_named(String username) throws Throwable {
        client = aClient().withUsername(username).build();
    }

    @When("^(?:she|he) opens a bank account$")
    public void he_opens_a_bank_account() throws Throwable {
        clientAccount = new Account(client);
        accountService.save(clientAccount);
    }

    @Then("^the (?:new|initial) balance is ([^\"]*) EUR$")
    public void the_balance_is_EUR(double expectedBalance) throws Throwable {
        assertThat(clientAccount.balance(), is(expectedBalance));
    }

    @Given("^an existing client named \"([^\"]*)\" with ([^\"]*) EUR in (?:her|his) account$")
    public void an_existing_client_named_with_EUR_in_her_account(String username, double balance) throws Throwable {
        client = aClient().withUsername(username).build();
        clientAccount = new Account(client);
        clientAccount.deposit(balance);
    }

    @When("^(?:she|he) deposits ([^\"]*) EUR into (?:her|his) account$")
    public void she_deposits_EUR_into_her_account(double amount) throws Throwable {
        try {
            clientAccount.deposit(amount);
        } catch (IllegalStateException ignored) {
        }
    }
}
