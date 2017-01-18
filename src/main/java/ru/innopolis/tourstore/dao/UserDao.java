package ru.innopolis.tourstore.dao;

import ru.innopolis.tourstore.entity.UserEntity;
import ru.innopolis.tourstore.exception.UserDaoException;

import java.util.List;

public interface UserDao {

    List<UserEntity> getAll() throws UserDaoException;
    UserEntity getEntityById(int id) throws UserDaoException;
    UserEntity getEntityByName(String name) throws UserDaoException;
    void update(UserEntity entity) throws UserDaoException;
    void delete(int id) throws UserDaoException;
    void create(UserEntity entity) throws UserDaoException;
}
