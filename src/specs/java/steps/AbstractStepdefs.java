package steps;

import org.glassfish.jersey.jackson.JacksonFeature;

import javax.ws.rs.client.WebTarget;

import static javax.ws.rs.client.ClientBuilder.newClient;
import static net.diegolemos.bankapp.Server.BANK_APP;

public abstract class AbstractStepdefs {
    protected WebTarget resource(String path) {
        javax.ws.rs.client.Client client = newClient();
        client.register(JacksonFeature.class);
        return client.target(BANK_APP).path(path);
    }
}
