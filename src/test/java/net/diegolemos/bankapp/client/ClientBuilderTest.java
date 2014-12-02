package net.diegolemos.bankapp.client;

import org.junit.Test;

import java.time.LocalDate;
import java.util.Date;

import static java.time.ZoneOffset.UTC;
import static net.diegolemos.bankapp.client.ClientBuilder.aClient;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class ClientBuilderTest {

    public static final Date _2010_JAN_30 = newDate(2000, 1, 30);

    @Test public void
    should_build_client() {
        Client bob = new Client();
        bob.setUsername("bob");
        bob.setBirthday(_2010_JAN_30);

        assertThat(aClient().withUsername("bob").withBirthday(_2010_JAN_30).build(), equalTo(bob));
    }

    private static Date newDate(int year, int month, int day) {
        LocalDate localDate = LocalDate.of(year, month, day);
        long epochMillis = localDate.atStartOfDay().toEpochSecond(UTC) * 1000;
        return new Date(epochMillis);
    }
}
