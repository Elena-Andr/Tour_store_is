package ru.innopolis.tourstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.innopolis.tourstore.dao.UserDao;
import ru.innopolis.tourstore.entity.User;
import ru.innopolis.tourstore.exception.UserDaoException;
import ru.innopolis.tourstore.filter.PasswordAuthentication;

import java.util.Arrays;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public List<User> getAll() throws UserDaoException{
        return userDao.getAll();
    }

    @Override
    public User getEntityById(int id) throws UserDaoException{
        return userDao.getEntityById(id);
    }

    @Override
    public void update(User entity) throws UserDaoException{
        userDao.update(entity);
    }

    @Override
    public void delete(int id) throws UserDaoException{
        userDao.delete(id);
    }

    @Override
    public void create(User entity) throws UserDaoException{
        userDao.create(entity);
    }

    @Override
    public boolean isUserAlreadyRegistered(String userName) throws UserDaoException {
        List<User> userList = getAll();

        for(User user : userList){
            if(user.getName().equals(userName)){
                return true;
            }
        }
        return false;
    }

    @Override
    public User validateLogin(String userName, String password) throws UserDaoException {
        byte[] hashedPassword = PasswordAuthentication.hashPassword(password, new byte[]{});

        List<User> users = getAll();

        for (User user : users) {
            if (user.getName().equals(userName) && Arrays.equals(hashedPassword, user.getPassword())) {
                return user;
            }
        }
        return null;
    }
}
