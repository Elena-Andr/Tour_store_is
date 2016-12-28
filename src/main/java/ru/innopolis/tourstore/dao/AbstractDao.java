package ru.innopolis.tourstore.dao;

import java.util.List;

/**
 * Abstract class for DAO objects
 * @param <T> entity for DAO
 */
public abstract class AbstractDao<T> {

    public abstract List<T> getAll() throws Exception;
    public abstract T getEntityById(int id) throws Exception;
    public abstract void update(T entity) throws Exception;
    public abstract void delete(int id) throws Exception;
    public abstract void create(T entity) throws Exception;
}
