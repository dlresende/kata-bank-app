package net.diegolemos.bankapp.client;

import javax.inject.Inject;
import java.util.Collection;

public class ClientService {
    private final ClientRepository clients;

    @Inject
    public ClientService(ClientRepository clients) {
        this.clients = clients;
    }

    public void save(Client client) {
        clients.add(client);
    }

    public Client findByUsername(String username) {
        return clients.withUsername(username);
    }

    public Collection<Client> all() {
        return clients.all();
    }
}