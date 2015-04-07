package net.diegolemos.bankapp.client;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class ClientRepository {

    private final Map<String, Client> allClients = new HashMap<>();

    public void add(Client client) {
        allClients.put(client.username(), client);
    }

    public Client withUsername(String username) {
        return allClients.get(username);
    }

    public Collection<Client> all() {
        return allClients.values();
    }
}
