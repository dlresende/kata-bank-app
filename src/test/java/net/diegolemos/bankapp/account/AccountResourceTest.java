package net.diegolemos.bankapp.account;

import net.diegolemos.bankapp.AbstractHttpTest;
import org.junit.Test;

import javax.ws.rs.client.WebTarget;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;

public class AccountResourceTest extends AbstractHttpTest {

    private AccountRepository accounts = injectMock(AccountRepository.class);
    private WebTarget accountResource = resource("account");

    @Test public void
    should_get_the_account_balance_for_a_given_user() {
        given(accounts.findForClient("bob")).willReturn(new Account());

        Account bobAccount = accountResource.path("bob").request().get(Account.class);

        assertThat(bobAccount.getBalance(), equalTo(0.0));
    }
}
