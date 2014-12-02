package steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.diegolemos.bankapp.account.Account;
import net.diegolemos.bankapp.client.Client;

import javax.ws.rs.client.WebTarget;

import static javax.ws.rs.client.Entity.json;
import static net.diegolemos.bankapp.client.ClientBuilder.aClient;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class AccountStepdefs extends AbstractStepdefs {

    private final WebTarget clientResource = resource("client");
    private final WebTarget accountResource = resource("account");

    private Client client;

    @And("^the account balance for \"([^\"]*)\" is (\\d+) EUR$")
    public void the_balance_for_user_is_EUR(String username, double balance) throws Throwable {
        Account userAccount = accountResource.path(username).request().get(Account.class);
        assertThat(userAccount.balance(), is(balance));
    }

    @When("^\"([^\"]*)\" deposits (\\d+) EUR in her bank account$")
    public void deposits_EUR_on_the_bank_account(String username, double amount) throws Throwable {
        Account userAccount = accountResource.path(username).request().get(Account.class);
        userAccount.deposit(amount);
        accountResource.request().put(json(userAccount));
    }

    @And("^a new bank account for \"([^\"]*)\"$")
    public void a_new_bank_account_for(String username) throws Throwable {
        Client client = clientResource.path(username).request().get(Client.class);
        Account userAccount = new Account(client);
        accountResource.request().put(json(userAccount));
    }

    @Given("^an existing user \"([^\"]*)\"$")
    public void an_existing_user(String username) throws Throwable {
        client = aClient().withUsername(username).build();
        clientResource.path(username).request().put(json(client));
    }

    @When("^the user creates a bank account$")
    public void the_user_creates_a_bank_account() throws Throwable {
        Account userAccount = new Account(client);
        accountResource.request().put(json(userAccount));
    }

    @Then("^balance of his new account is (.+) EUR$")
    public void balance_of_his_new_account_is_EUR(double amount) throws Throwable {
        Account account = accountResource.path(client.username()).request().get(Account.class);
        assertThat(account.balance(), is(amount));
    }

    @Given("^an existing client with username \"([^\"]*)\"$")
    public void an_existing_client_with_username(String username) throws Throwable {
        client = aClient().withUsername(username).build();
        clientResource.path(username).request().put(json(client));
    }

    @And("^the balance for the client account is \"([^\"]*)\"$")
    public void the_balance_for_the_client_account_is(double amount) throws Throwable {
        Account userAccount = new Account(client);
        assertThat(userAccount.balance(), equalTo(amount));
        accountResource.request().put(json(userAccount));
    }

    @When("^the client deposits \"([^\"]*)\" EUR into his account$")
    public void the_client_deposits_EUR_into_his_account(double amount) throws Throwable {
        Account account = accountResource.path(client.username()).request().get(Account.class);
        try {
            account.deposit(amount);
            accountResource.request().put(json(account));
        } catch (IllegalStateException ignored) {
        }
    }

    @Then("^the account balance is \"([^\"]*)\" EUR$")
    public void the_account_balance_is_EUR(double expectedBalance) throws Throwable {
        Account account = accountResource.path(client.username()).request().get(Account.class);
        assertThat(account.balance(), is(expectedBalance));
    }

    @When("^a bank account is created for the client$")
    public void a_bank_account_is_created_for_the_client() throws Throwable {
        Account userAccount = new Account(client);
        accountResource.request().put(json(userAccount));
    }
}
