package net.diegolemos.bankapp.client;

import java.util.Date;

public class ClientBuilder {
    private String username;
    private Date birthday;

    public static ClientBuilder aClient() {
        return new ClientBuilder();
    }

    public Client build() {
        return new Client(username, birthday);
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
