package net.diegolemos.bankapp.client;

import java.util.Date;

public class Client {
    private String username;
    private Date birthday;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Client client = (Client) o;

        if (birthday != null ? !birthday.equals(client.birthday) : client.birthday != null) return false;
        if (username != null ? !username.equals(client.username) : client.username != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = username != null ? username.hashCode() : 0;
        result = 31 * result + (birthday != null ? birthday.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Client{" +
                "username='" + username + '\'' +
                ", birthday=" + birthday +
                '}';
    }
}
