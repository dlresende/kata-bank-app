package net.diegolemos.bankapp.client;

public class Client {
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Client client = (Client) o;

        return !(username != null ? !username.equals(client.username) : client.username != null);
    }

    @Override
    public int hashCode() {
        return username != null ? username.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Client{" +
                "username='" + username + '\'' +
                '}';
    }
}
