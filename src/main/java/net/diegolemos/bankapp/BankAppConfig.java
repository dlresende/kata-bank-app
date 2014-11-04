package net.diegolemos.bankapp;

import org.glassfish.hk2.api.Factory;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;

public class BankAppConfig extends ResourceConfig {

    public BankAppConfig() {
        packages("net.diegolemos.bankapp");
        register(new BankAppBinder());
        register(JacksonFeature.class);
    }

    public static <T> Factory<T> factory(T instance) {
        return new Factory<T>() {
            @Override
            public T provide() {
                return instance;
            }

            @Override
            public void dispose(T instance) {}
        };
    }
}
