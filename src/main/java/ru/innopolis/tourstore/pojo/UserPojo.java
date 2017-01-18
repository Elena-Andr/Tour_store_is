package ru.innopolis.tourstore.pojo;

public class UserPojo {

    private int id;
    private String name;
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
