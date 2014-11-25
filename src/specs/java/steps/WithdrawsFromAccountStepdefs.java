package steps;

import cucumber.api.java.en.When;
import net.diegolemos.bankapp.account.Account;
import net.diegolemos.bankapp.transaction.Transaction;

import javax.ws.rs.client.WebTarget;

import static javax.ws.rs.client.Entity.json;
import static net.diegolemos.bankapp.transaction.Transaction.Action.WITHDRAW;

public class WithdrawsFromAccountStepdefs extends AbstractStepdefs {
    private WebTarget accountResource = resource("account");

    @When("^\"([^\"]*)\" withdraws (\\d+) EUR from his account$")
    public void withdraws_EUR_from_his_account(String username, int amount) throws Throwable {
        Account account = accountResource.path(username).request().get(Account.class);

        Transaction withdraw = new Transaction();
        withdraw.setAction(WITHDRAW);
        withdraw.setAmount(amount);

        account.addTransaction(withdraw);

        accountResource.request().put(json(account));

    }
}
