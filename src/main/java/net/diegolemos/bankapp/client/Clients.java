package net.diegolemos.bankapp.client;

import java.util.HashMap;
import java.util.Map;

public class Clients {

    private final Map<String, Client> allClients = new HashMap<>();

    public void put(String username, Client client) {
        allClients.put(username, client);
    }

    public Client get(String username) {
        return allClients.get(username);
    }
}
