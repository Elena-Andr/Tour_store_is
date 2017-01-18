package ru.innopolis.tourstore.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Arrays;

/**
 * Object represents entity "Tour"
 */
@Entity
@Table(name = "TOURS")
public class TourEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true, nullable = false)
    private int id;

    @Column(name = "DELETED")
    private boolean isDeleted;

    @Column(name = "NAME")
    @NotNull(message="Cannot be Null")
    @Size(min=3, max=30, message = "Invalid input")
    private String name;

    @Column(name = "DESCRIPTION")
    @NotNull(message="Cannot be Null")
    @Size(min=3, max=30, message = "Invalid input")
    private String description;

    public boolean isDeleted() {return isDeleted;}

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
