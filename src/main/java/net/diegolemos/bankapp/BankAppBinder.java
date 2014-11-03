package net.diegolemos.bankapp;

import net.diegolemos.bankapp.account.AccountRepository;
import net.diegolemos.bankapp.client.ClientRepository;
import org.glassfish.hk2.api.Factory;
import org.glassfish.hk2.utilities.binding.AbstractBinder;

import javax.inject.Singleton;

public class BankAppBinder extends AbstractBinder {

    @Override
    protected void configure() {
        bindFactory(factory(new ClientRepository())).to(ClientRepository.class).in(Singleton.class);
        bindFactory(factory(new AccountRepository())).to(AccountRepository.class).in(Singleton.class);
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
