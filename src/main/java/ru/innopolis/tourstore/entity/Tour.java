package ru.innopolis.tourstore.entity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Object represents entity "Tour"
 */
public class Tour {
    private static final Logger LOG = LoggerFactory.getLogger(Tour.class);

    private int id;
    private String name;
    private String description;
    private boolean isDeleted;

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
