package steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.diegolemos.bankapp.client.Client;

import javax.ws.rs.client.WebTarget;

import static javax.ws.rs.client.Entity.json;
import static net.diegolemos.bankapp.client.ClientBuilder.aClient;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class CreateUserStepdefs extends AbstractStepdefs {

    private final WebTarget clientResource = resource("client");

    private String username;

    @Given("^a new user \"([^\"]*)\"$")
    public void a_new_user(String username) throws Throwable {
        this.username = username;
    }

    @When("^an account is created for the user$")
    public void an_account_is_created_for_the_user() throws Throwable {
        Client newClient = aClient().withUsername(username).build();
        clientResource.path(username).request().put(json(newClient));
    }

    @Then("^the user information is stored in the system$")
    public void the_user_information_is_stored_in_the_system() throws Throwable {
        Client client = clientResource.path(username).request().get(Client.class);
        assertThat(client, equalTo(aClient().withUsername(username).build()));
    }
}
