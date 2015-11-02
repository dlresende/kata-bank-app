package net.diegolemos.bankapp.steps.client;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.diegolemos.bankapp.client.Client;
import net.diegolemos.bankapp.client.ClientRepository;
import net.diegolemos.bankapp.client.ClientService;

import java.util.Date;
import java.util.List;

import static jersey.repackaged.com.google.common.collect.Iterables.getOnlyElement;
import static net.diegolemos.bankapp.client.ClientBuilder.aClient;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class ClientStepdefs {

    private ClientRepository clients;
    private ClientService clientService;
    private Client client;

    @Before
    public void setUp() {
        clients = mock(ClientRepository.class);
        clientService = new ClientService(clients);
    }

    @Given("^\"([^\"]*)\" born on \"([^\"]*)\"$")
    public void born_on(String username, Date birthday) throws Throwable {
        client = aClient().withUsername(username).withBirthday(birthday).build();
    }

    @When("^he becomes a client$")
    public void he_becomes_a_client() throws Throwable {
        clientService.save(client);
    }

    @Then("^the following information should be stored in the system:$")
    public void the_following_information_should_be_stored_in_the_system(List<Client> expectedClients) throws Throwable {
        Client expectedClient = getOnlyElement(expectedClients);

        verify(clients).add(expectedClient);
    }
}
