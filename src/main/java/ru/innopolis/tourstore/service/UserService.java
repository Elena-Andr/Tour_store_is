package ru.innopolis.tourstore.service;

import ru.innopolis.tourstore.entity.UserEntity;
import ru.innopolis.tourstore.exception.UserDaoException;

import java.util.List;

public interface UserService {
    List<UserEntity> getAll() throws UserDaoException;
    UserEntity getEntityById(int id) throws UserDaoException;
    void update(UserEntity entity) throws UserDaoException;
    void delete(int id) throws UserDaoException;
    void create(UserEntity entity) throws UserDaoException;
    boolean isUserAlreadyRegistered(String userName) throws UserDaoException;
    UserEntity getEntityByName(String userName) throws UserDaoException;
}
