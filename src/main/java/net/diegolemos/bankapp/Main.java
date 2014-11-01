package net.diegolemos.bankapp;

import net.diegolemos.bankapp.account.AccountRepository;
import net.diegolemos.bankapp.client.ClientRepository;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.hk2.api.Factory;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;

import javax.inject.Singleton;
import java.io.IOException;
import java.net.URI;

import static org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory.createHttpServer;

public class Main {
    public static final String BANK_APP = "http://localhost:8000/bankapp/";

    public static HttpServer startServer() {
        return startServer(new BankAppBinder());
    }

    public static HttpServer startServer(AbstractBinder binder) {
        ResourceConfig config = new ResourceConfig();
        config.packages("net.diegolemos.bankapp");
        config.register(binder);
        return createHttpServer(URI.create(BANK_APP), config);
    }

    private static <T> Factory<T> factory(T t) {
        return new Factory<T>() {
            @Override
            public T provide() {
                return t;
            }

            @Override
            public void dispose(T instance) {
            }
        };
    }

    public static class BankAppBinder extends AbstractBinder {
        @Override
        protected void configure() {
            bindFactory(factory(new ClientRepository())).to(ClientRepository.class).in(Singleton.class);
            bindFactory(factory(new AccountRepository())).to(AccountRepository.class).in(Singleton.class);
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

