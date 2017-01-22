package ru.innopolis.tourstore.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * Object represents entity "User"
 */
@Entity
@Table(name = "USERS")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true, nullable = false)
    private int id;

    @Column(name = "NAME")
    @NotNull(message = "Name cannot be empty")
    private String name;

    @Column(name = "PASSWORD")
    @NotNull(message = "Password cannot be empty")
    private String password;

    @Column(name = "ENABLED")
    private Boolean enabled;

    @Column(name = "ROLE")
    private String role;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "userEntity")
    private List<OrderEntity> orders;

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

    public List<OrderEntity> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderEntity> orders) {
        this.orders = orders;
    }
}
