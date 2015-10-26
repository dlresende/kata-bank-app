package net.diegolemos.bankapp;

import net.diegolemos.bankapp.account.AccountRepository;
import net.diegolemos.bankapp.account.AccountResource;
import net.diegolemos.bankapp.account.AccountService;
import net.diegolemos.bankapp.client.ClientRepository;
import net.diegolemos.bankapp.client.ClientResource;
import net.diegolemos.bankapp.client.ClientService;
import org.glassfish.hk2.utilities.binding.AbstractBinder;

import javax.inject.Singleton;

public class BankAppBinder extends AbstractBinder {

    @Override
    protected void configure() {

        // Since we don't have a real storage (db), we need to keep repositories as Singleton
        // in order to keep objects inside them
        bind(ClientRepository.class).to(ClientRepository.class).in(Singleton.class);
        bind(ClientService.class).to(ClientService.class);
        bind(ClientResource.class).to(ClientResource.class);

        bind(AccountRepository.class).to(AccountRepository.class).in(Singleton.class);
        bind(AccountService.class).to(AccountService.class);
        bind(AccountResource.class).to(AccountResource.class);
    }
}
