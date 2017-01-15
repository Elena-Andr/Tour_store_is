package ru.innopolis.tourstore.entity;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Object represents entity "Tour"
 */
public class Tour {

    private int id;
    private boolean isDeleted;

    @NotNull(message="Cannot be Null")
    @Size(min=3, max=30, message = "Invalid input")
    private String name;

    @NotNull(message="Cannot be Null")
    @Size(min=3, max=30, message = "Invalid input")
    private String description;

    public boolean isDeleted() {
        return isDeleted;
    }

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
