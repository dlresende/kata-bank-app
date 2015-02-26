package net.diegolemos.bankapp.steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.diegolemos.bankapp.client.Client;

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

    private Client client;

    @Given("^\"([^\"]*)\" born on \"([^\"]*)\"$")
    public void born_on(String username, Date birthday) throws Throwable {
        client = aClient().withUsername(username).withBirthday(birthday).build();
    }

    @When("^he becomes a client$")
    public void he_becomes_a_client() throws Throwable {
        clientResource.path(client.username()).request().put(json(client));
    }

    @Then("^the following client information should be stored in the system:$")
    public void the_following_client_information_should_be_stored_in_the_system(List<Client> expectedClients) throws Throwable {
        Client expectedClient = getOnlyElement(expectedClients);
        Client existingClient = clientResource.path(client.username()).request().get(Client.class);
        assertThat(existingClient, equalTo(expectedClient));
    }

    @Then("^the following information should be stored in the system:$")
    public void the_following_information_should_be_stored_in_the_system(List<Client> expectedClients) throws Throwable {
        Client expectedClient = getOnlyElement(expectedClients);
        Client existingClient = clientResource.path(client.username()).request().get(Client.class);
        assertThat(existingClient, equalTo(expectedClient));
    }
}
