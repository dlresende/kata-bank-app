package net.diegolemos.bankapp.steps.account;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.diegolemos.bankapp.BankAppBinder;
import net.diegolemos.bankapp.MyServer;
import net.diegolemos.bankapp.client.Client;
import net.diegolemos.bankapp.steps.client.ClientUI;
import net.diegolemos.bankapp.steps.client.ClientUnit;
import org.glassfish.grizzly.http.server.HttpServer;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class AccountStepdefs {

    private net.diegolemos.bankapp.steps.client.Client client;
    private Account account;
    private HttpServer server;
    private WebDriver webDriver;

    @Before("@ui")
    public void setUpUI() {
        server = MyServer.startServer(new BankAppBinder(), "8082");
        webDriver = new FirefoxDriver();
        account = new AccountUI(webDriver);
        client = new ClientUI(webDriver);
    }

    @Before("~@ui")
    public void setUp() {
        client = new ClientUnit();
        account = new AccountUnit();
    }

    @Given("^an existing client named \"([^\"]*)\"$")
    public void an_existing_client_named(String username) throws Throwable {
        client.createBy(username);
    }

    @When("^(?:she|he) opens a bank account$")
    public void he_opens_a_bank_account() throws Throwable {
        Client client = this.client.get();
        account.createFor(client);
    }

    @Then("^the (?:new|initial) balance is ([^\"]*) EUR$")
    public void the_balance_is_EUR(double expectedBalance) throws Throwable {
        account.checkBalance(expectedBalance);
    }

    @Given("^an existing client named \"([^\"]*)\" with ([^\"]*) EUR in (?:her|his) account$")
    public void an_existing_client_named_with_EUR_in_her_account(String username, double balance) throws Throwable {
        client.createBy(username);
        account.createFor(client.get());
        account.deposit(balance);
    }

    @When("^(?:she|he) deposits ([^\"]*) EUR into (?:her|his) account$")
    public void she_deposits_EUR_into_her_account(double amount) throws Throwable {
        try {
            account.deposit(amount);
        } catch (IllegalStateException ignored) {
        }
    }

    @After("@ui")
    public void tearDownUI() {
        webDriver.quit();
        server.shutdown();
    }
}
