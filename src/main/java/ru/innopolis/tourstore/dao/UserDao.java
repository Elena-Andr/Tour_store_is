package ru.innopolis.tourstore.dao;

import ru.innopolis.tourstore.entity.User;
import ru.innopolis.tourstore.exception.UserDaoException;

import java.util.List;

public interface UserDao {

    List<User> getAll() throws UserDaoException;
    User getEntityById(int id) throws UserDaoException;
    User getEntityByName(String name) throws UserDaoException;
    void update(User entity) throws UserDaoException;
    void delete(int id) throws UserDaoException;
    void create(User entity) throws UserDaoException;
}
