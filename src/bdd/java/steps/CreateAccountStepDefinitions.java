package steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.diegolemos.bankapp.account.Account;
import net.diegolemos.bankapp.client.Client;
import org.glassfish.jersey.jackson.JacksonFeature;

import javax.ws.rs.client.WebTarget;

import static javax.ws.rs.client.ClientBuilder.newClient;
import static javax.ws.rs.client.Entity.json;
import static net.diegolemos.bankapp.Main.BANK_APP;
import static net.diegolemos.bankapp.client.ClientBuilder.aClient;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class CreateAccountStepDefinitions {

    private String username;

    @Given("^a new user \"([^\"]*)\"$")
    public void a_new_user(String username) throws Throwable {
        this.username = username;
    }

    private WebTarget resource(String path) {
        javax.ws.rs.client.Client client = newClient();
        client.register(JacksonFeature.class);
        return client.target(BANK_APP).path(path);
    }

    @When("^the user becomes client$")
    public void the_user_becomes_client() throws Throwable {
        Client newClient = aClient().withUsername(username).build();
        resource("client").path(username).request().put(json(newClient));
    }

    @Then("^balance of his new account is (\\d+) EUR$")
    public void balance_of_his_new_account_is_EUR(double amount) throws Throwable {
        Account account = resource("account/" + username).request().get(Account.class);
        assertThat(account.getBalance(), equalTo(amount));
    }
}
