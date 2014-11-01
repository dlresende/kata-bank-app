package net.diegolemos;

import net.diegolemos.bankapp.client.Clients;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.hk2.api.Factory;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;

import javax.inject.Singleton;
import java.io.IOException;
import java.net.URI;

import static org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory.createHttpServer;

public class Main {
    public static final String BANK_APP = "http://localhost:8080/bankapp/";

    public static HttpServer startServer() {
        ResourceConfig config = new ResourceConfig();
        config.packages("net.diegolemos");
        config.register(new BankAppBinder());
        return createHttpServer(URI.create(BANK_APP), config);
    }

    static class ClientsFactory implements Factory<Clients> {
        @Override
        public Clients provide() {
            return new Clients();
        }

        @Override
        public void dispose(Clients clients) {
        }
    }

    private static class BankAppBinder extends AbstractBinder {
        @Override
        protected void configure() {
            bindFactory(ClientsFactory.class).to(Clients.class).in(Singleton.class);
        }
    }

    public static void main(String[] args) throws IOException {
        final HttpServer server = startServer();
        System.out.println(String.format("Jersey app started with WADL available at "
                + "%sapplication.wadl\nHit enter to stop it...", BANK_APP));
        System.in.read();
        server.stop();
    }
}

