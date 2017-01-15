package ru.innopolis.tourstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.innopolis.tourstore.dao.UserDao;
import ru.innopolis.tourstore.entity.User;
import ru.innopolis.tourstore.exception.UserDaoException;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao){
        this.userDao = userDao;
    }

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
    public User getEntityByName(String userName) throws UserDaoException {
        return userDao.getEntityByName(userName);
    }
}
