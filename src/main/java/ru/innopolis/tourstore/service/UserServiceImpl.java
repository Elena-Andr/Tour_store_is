package ru.innopolis.tourstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.innopolis.tourstore.dao.UserDao;
import ru.innopolis.tourstore.entity.UserEntity;
import ru.innopolis.tourstore.exception.UserDaoException;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserDao userDao;

    @Autowired
    public UserServiceImpl(@Qualifier("userDaoImplHibernate") UserDao userDao){
        this.userDao = userDao;
    }

    @Override
    public List<UserEntity> getAll() throws UserDaoException{
        return userDao.getAll();
    }

    @Override
    public UserEntity getEntityById(int id) throws UserDaoException{
        return userDao.getEntityById(id);
    }

    @Override
    public void update(UserEntity entity) throws UserDaoException{
        userDao.update(entity);
    }

    @Override
    public void delete(int id) throws UserDaoException{
        userDao.delete(id);
    }

    @Override
    public void create(UserEntity entity) throws UserDaoException{
        userDao.create(entity);
    }

    @Override
    public boolean isUserAlreadyRegistered(String userName) throws UserDaoException {
        List<UserEntity> userList = getAll();

        for(UserEntity user : userList){
            if(user.getName().equals(userName)){
                return true;
            }
        }
        return false;
    }

    @Override
    public UserEntity getEntityByName(String userName) throws UserDaoException {
        return userDao.getEntityByName(userName);
    }
}
