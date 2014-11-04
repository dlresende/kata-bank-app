package net.diegolemos.bankapp;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;

import java.io.IOException;
import java.net.URI;

import static org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory.createHttpServer;

public class Server {
    public static final String BANK_APP = "http://localhost:8081/api/";

    public static HttpServer startServer() {
        return createHttpServer(URI.create(BANK_APP), new BankAppConfig());
    }

    public static HttpServer startServer(AbstractBinder binder) {
        ResourceConfig config = new ResourceConfig();
        config.packages("net.diegolemos.bankapp");
        config.register(binder);
        return createHttpServer(URI.create(BANK_APP), config);
    }

    public static void main(String[] args) throws IOException {
        final HttpServer server = startServer();
        System.out.println(String.format("Jersey app started with WADL available at "
                + "%sapplication.wadl\nHit enter to stop it...", BANK_APP));
        System.in.read();
        server.stop();
    }
}

