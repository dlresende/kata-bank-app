package steps;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.diegolemos.bankapp.client.Client;
import net.diegolemos.bankapp.client.ClientBuilder;

import javax.ws.rs.client.WebTarget;
import java.util.Date;
import java.util.List;

import static javax.ws.rs.client.Entity.json;
import static jersey.repackaged.com.google.common.collect.Iterables.getOnlyElement;
import static net.diegolemos.bankapp.client.ClientBuilder.aClient;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class ClientStepdefs extends AbstractStepdefs {

    private final WebTarget clientResource = resource("client");

    private ClientBuilder client;

    @Before
    public void refresh() {
        client = aClient();
    }

    @Given("^a person born in \"([^\"]*)\"$")
    public void a_person_born_in(Date birthday) throws Throwable {
        client.withBirthday(birthday);
    }

    @When("^this person becomes a client with the username \"([^\"]*)\"$")
    public void this_person_becomes_a_client_with_the_username(String username) throws Throwable {
        Client client = this.client.withUsername(username).build();
        clientResource.path(username).request().put(json(client));
    }

    @Then("^the following client information should be stored in the system:$")
    public void the_following_client_information_should_be_stored_in_the_system(List<Client> expectedClients) throws Throwable {
        Client expectedClient = getOnlyElement(expectedClients);
        Client existingClient = clientResource.path(client.build().username()).request().get(Client.class);
        assertThat(existingClient, equalTo(expectedClient));
    }
}
