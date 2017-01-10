package ru.innopolis.tourstore.service;

import ru.innopolis.tourstore.entity.User;
import ru.innopolis.tourstore.exception.UserDaoException;

import java.util.List;

public interface UserService {
    List<User> getAll() throws UserDaoException;
    User getEntityById(int id) throws UserDaoException;
    void update(User entity) throws UserDaoException;
    void delete(int id) throws UserDaoException;
    void create(User entity) throws UserDaoException;
    boolean isUserAlreadyRegistered(String userName) throws UserDaoException;
    User validateLogin(User entity) throws UserDaoException;
}
