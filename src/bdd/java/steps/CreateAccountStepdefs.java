package steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.diegolemos.bankapp.account.Account;
import net.diegolemos.bankapp.client.Client;

import javax.ws.rs.client.WebTarget;

import static javax.ws.rs.client.Entity.json;
import static net.diegolemos.bankapp.account.AccountBuilder.anAccount;
import static net.diegolemos.bankapp.client.ClientBuilder.aClient;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class CreateAccountStepdefs extends AbstractStepdefs {

    private final WebTarget clientResource = resource("client");
    private final WebTarget accountResource = resource("account");

    private Client user;

    @Given("^an existing user \"([^\"]*)\"$")
    public void an_existing_user(String username) throws Throwable {
        user = aClient().withUsername(username).build();
        clientResource.path(username).request().put(json(user));
    }

    @When("^the user creates a bank account$")
    public void the_user_creates_a_bank_account() throws Throwable {
        Account userAccount = anAccount().withHolder(user).build();
        accountResource.request().put(json(userAccount));
    }

    @Then("^balance of his new account is (.+) EUR$")
    public void balance_of_his_new_account_is_EUR(double amount) throws Throwable {
        Account account = accountResource.path(user.getUsername()).request().get(Account.class);
        assertThat(account.getBalance(), equalTo(amount));
    }
}
