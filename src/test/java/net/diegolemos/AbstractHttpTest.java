package net.diegolemos;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.junit.After;
import org.junit.Before;

import javax.ws.rs.client.WebTarget;

import static javax.ws.rs.client.ClientBuilder.newClient;
import static net.diegolemos.Main.BANK_APP;
import static net.diegolemos.Main.startServer;

public abstract class AbstractHttpTest {

    private HttpServer server;

    @Before
    public void setUp() throws Exception {
        server = startServer();
    }

    protected WebTarget resource(String path) {
        javax.ws.rs.client.Client client = newClient();
        client.register(JacksonFeature.class);
        return client.target(BANK_APP).path(path);
    }

    @After
    public void tearDown() throws Exception {
        server.stop();
    }
}
