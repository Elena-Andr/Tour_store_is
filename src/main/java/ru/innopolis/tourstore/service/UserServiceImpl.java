package ru.innopolis.tourstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.innopolis.tourstore.dao.UserDao;
import ru.innopolis.tourstore.entity.User;
import ru.innopolis.tourstore.exception.UserDaoException;
import ru.innopolis.tourstore.filter.PasswordAuthentication;
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
        entity.setSalt(PasswordAuthentication.generateSalt());
        String hashedPassword = PasswordAuthentication.hashPassword(entity.getPassword().trim(), entity.getSalt());
        entity.setPassword(hashedPassword);
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
    public User validateLogin(User entity) throws UserDaoException {
        List<User> users = userDao.getAll();

        for (User user : users) {
            String hashedPassword = PasswordAuthentication.hashPassword(entity.getPassword(), user.getSalt());
            if (user.getName().equals(entity.getName()) && hashedPassword.equals(user.getPassword())) {
                return user;
            }
        }
        return null;
    }
}
