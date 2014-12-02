package net.diegolemos.bankapp.client;

import org.junit.Test;

import java.time.LocalDate;
import java.util.Date;

import static java.time.ZoneOffset.UTC;
import static net.diegolemos.bankapp.client.ClientBuilder.aClient;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class ClientBuilderTest {

    public static final Date TODAY = new Date();

    @Test public void
    should_build_client() {
        Client bob = new Client("bob", TODAY);

        assertThat(aClient().withUsername("bob").withBirthday(TODAY).build(), equalTo(bob));
    }
}
