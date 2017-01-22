package ru.innopolis.tourstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.innopolis.tourstore.entity.UserEntity;
import ru.innopolis.tourstore.exception.UserDaoException;
import ru.innopolis.tourstore.repository.UserRepository;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserEntity> getAll() throws UserDaoException{
        return userRepository.findAll();
    }

    @Override
    public UserEntity getEntityById(int id) throws UserDaoException{
        return userRepository.findOne(id);
    }

    @Override
    public void update(UserEntity entity) throws UserDaoException{
        userRepository.saveAndFlush(entity);

    }

    @Override
    public void delete(int id) throws UserDaoException{
        userRepository.delete(id);
    }

    @Override
    public void create(UserEntity entity) throws UserDaoException{
        userRepository.saveAndFlush(entity);
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
        return userRepository.findByName(userName).get(0);
    }
}
