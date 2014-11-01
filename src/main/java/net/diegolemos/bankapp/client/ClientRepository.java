package net.diegolemos.bankapp.client;

import java.util.HashMap;
import java.util.Map;

public class ClientRepository {

    private final Map<String, Client> allClients = new HashMap<>();

    public void add(String username, Client client) {
        allClients.put(username, client);
    }

    public Client withUsername(String username) {
        return allClients.get(username);
    }
}
