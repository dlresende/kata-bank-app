package steps;

import cucumber.api.java.en.When;
import net.diegolemos.bankapp.account.Account;
import net.diegolemos.bankapp.transaction.Transaction;

import javax.ws.rs.client.WebTarget;

import static javax.ws.rs.client.Entity.json;

public class WithdrawStepdefs extends AbstractStepdefs {

    private final WebTarget accountResource = resource("account");

    @When("^\"([^\"]*)\" withdraws (\\d+) euros in her bank account$")
    public void withdraws_euros_in_her_bank_account(String username, int amount) throws Throwable {
        Account userAccount = accountResource.path(username).request().get(Account.class);

        Transaction withdraw = new Transaction();
        withdraw.setAction(Transaction.Action.WITHDRAW);
        withdraw.setAmount(amount);

        userAccount.addTransaction(withdraw);

        accountResource.request().put(json(userAccount));

    }
}
