package steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.When;
import net.diegolemos.bankapp.account.Account;
import net.diegolemos.bankapp.client.Client;

import javax.ws.rs.client.WebTarget;

import static javax.ws.rs.client.Entity.json;
import static net.diegolemos.bankapp.account.AccountBuilder.anAccount;
import static net.diegolemos.bankapp.transaction.TransactionBuilder.aDeposit;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class DepositIntoAccountStepdefs extends AbstractStepdefs {

    private final WebTarget clientResource = resource("client");
    private final WebTarget accountResource = resource("account");

    @And("^the account balance for \"([^\"]*)\" is (\\d+) EUR$")
    public void the_balance_for_user_is_EUR(String username, double balance) throws Throwable {
        Account userAccount = accountResource.path(username).request().get(Account.class);
        assertThat(userAccount.balance(), equalTo(balance));
    }

    @When("^\"([^\"]*)\" deposits (\\d+) euros in her bank account$")
    public void deposits_euros_on_the_bank_account(String username, double amount) throws Throwable {
        Account userAccount = accountResource.path(username).request().get(Account.class);
        userAccount.addTransaction(aDeposit().of(amount).build());
        accountResource.request().put(json(userAccount));
    }

    @And("^a bank account for \"([^\"]*)\"$")
    public void a_bank_account_for(String username) throws Throwable {
        Client client = clientResource.path(username).request().get(Client.class);
        Account userAccount = anAccount().withHolder(client).build();
        accountResource.request().put(json(userAccount));
    }
}
