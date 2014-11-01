package net.diegolemos.bankapp;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.hk2.api.Factory;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.junit.After;
import org.junit.Before;

import javax.inject.Singleton;
import javax.ws.rs.client.WebTarget;
import java.util.LinkedList;
import java.util.List;

import static javax.ws.rs.client.ClientBuilder.newClient;
import static net.diegolemos.bankapp.Server.*;
import static org.mockito.Mockito.mock;

public abstract class AbstractHttpTest {

    private List<AbstractBinder> binders = new LinkedList<>();

    private HttpServer server;

    @Before
    public void setUp() throws Exception {
        server = startServer(new BankAppBinderTest());
    }

    protected WebTarget resource(String path) {
        javax.ws.rs.client.Client client = newClient();
        client.register(JacksonFeature.class);
        return client.target(BANK_APP).path(path);
    }

    @After
    public void tearDown() throws Exception {
        server.stop();
        binders = new LinkedList<>();
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

    private class BankAppBinderTest extends AbstractBinder {
        @Override
        protected void configure() {
            binders.forEach(this::install);
            install(new BankAppBinder());
        }
    }

    private static <T> AbstractBinder binder(Class<T> clazz, T instance) {
        return new AbstractBinder() {
            @Override
            protected void configure() {
                bindFactory(factory(instance)).to(clazz).in(Singleton.class);
            }
        };
    }

    protected <T> T injectMock(Class<T> clazz) {
        T mock = mock(clazz);
        binders.add(binder(clazz, mock));
        return mock;
    }
}
