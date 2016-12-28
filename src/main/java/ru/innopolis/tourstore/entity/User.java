package ru.innopolis.tourstore.entity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Object represents entity "User"
 */
public class User {
    private static final Logger LOG = LoggerFactory.getLogger(User.class);

    private int id;
    private String name;
    private byte[] password;
    private String role;

    public User(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getPassword() {
        return password;
    }

    public void setPassword(byte[] password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
