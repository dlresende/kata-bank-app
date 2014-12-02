package net.diegolemos.bankapp.client;

import java.util.Date;

public class ClientBuilder {
    private String username;
    private Date birthday;

    public static ClientBuilder aClient() {
        return new ClientBuilder();
    }

    public Client build() {
        Client client = new Client();
        client.setUsername(this.username);
        client.setBirthday(this.birthday);
        return client;
    }

    public ClientBuilder withUsername(String username) {
        this.username = username;
        return this;
    }

    public ClientBuilder withBirthday(Date birthday) {
        this.birthday = birthday;
        return this;
    }
}
