package com.example.votingapp;

import java.util.List;

public class UserHolder {
    List<User> users;

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public UserHolder(List<User> users) {
        this.users = users;
    }
}
