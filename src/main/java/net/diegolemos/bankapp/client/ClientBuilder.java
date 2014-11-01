package net.diegolemos.bankapp.client;

public class ClientBuilder {
    private String username;

    public static ClientBuilder aClient() {
        return new ClientBuilder();
    }

    public ClientBuilder withUsername(String username) {
        this.username = username;
        return this;
    }

    public Client build() {
        Client client = new Client();
        client.setUsername(this.username);
        return client;
    }
}
