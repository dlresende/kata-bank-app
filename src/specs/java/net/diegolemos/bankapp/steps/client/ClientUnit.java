package net.diegolemos.bankapp.steps.client;

import net.diegolemos.bankapp.client.ClientBuilder;

public class ClientUnit implements Client {
    private net.diegolemos.bankapp.client.Client client;

    @Override
    public void createBy(String username) {
        client = ClientBuilder.aClient().withUsername(username).build();
    }

    @Override
    public net.diegolemos.bankapp.client.Client get() {
        return client;
    }
}