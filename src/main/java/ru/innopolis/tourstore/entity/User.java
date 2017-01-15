package ru.innopolis.tourstore.entity;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Object represents entity "User"
 */
public class User {

    private int id;

    @NotNull(message = "Name cannot be empty")
    @Size(min = 3, max = 10, message = "Invalid input")
    private String name;

    @NotNull(message = "Password cannot be empty")
    @Size(min = 3, max = 10, message = "Invalid input")
    private String password;

    private String role;
    private Boolean enabled;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Boolean isEnabled(){
        return enabled;
    }

    public void setEnabled(Boolean value){
        this.enabled = value;
    }
}
