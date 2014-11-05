package steps;

import cucumber.api.PendingException;
import cucumber.api.java.en.When;
import net.diegolemos.bankapp.account.Account;
import net.diegolemos.bankapp.transaction.TransactionBuilder;

import javax.ws.rs.client.WebTarget;

import static javax.ws.rs.client.Entity.json;
import static net.diegolemos.bankapp.transaction.TransactionBuilder.aDeposit;
import static net.diegolemos.bankapp.transaction.TransactionBuilder.aWithdraw;

public class WithdrawStepdefs extends AbstractStepdefs {
    private WebTarget accountResource = resource("account");

    @When("^\"([^\"]*)\" withdraws (\\d+) EUR from her bank account$")
    public void withdraws_EUR_from_her_bank_account(String username, int amount) throws Throwable {
        Account userAccount = accountResource.path(username).request().get(Account.class);
        userAccount.addTransaction(aWithdraw().of(amount).build());
        accountResource.request().put(json(userAccount));
    }
}
