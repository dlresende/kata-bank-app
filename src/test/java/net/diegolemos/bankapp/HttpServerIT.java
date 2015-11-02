package net.diegolemos.bankapp;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.junit.After;
import org.junit.Before;

import javax.ws.rs.client.WebTarget;

import static javax.ws.rs.client.ClientBuilder.newClient;

public abstract class HttpServerIT {

    private HttpServer server;

    @Before
    public void startServer() throws Exception {
        server = MyServer.startServer(new BankAppBinder(), "8081");
    }

    @After
    public void stopServer() throws Exception {
        server.stop();
    }

    protected WebTarget resource(String path) {
        javax.ws.rs.client.Client client = newClient();
        client.register(JacksonFeature.class);
        return client.target("http://localhost:8081/api/").path(path);
    }
}
