package ru.innopolis.tourstore.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.innopolis.tourstore.db.DatabaseConnector;
import ru.innopolis.tourstore.db.IDatabaseConnector;
import ru.innopolis.tourstore.entity.User;
import ru.innopolis.tourstore.exception.UserDaoException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static ru.innopolis.tourstore.db.SQLConstants.*;

/**
 * Object represents DAO for user entity
 */
public class UserDao extends AbstractDao<User>{
    private static final Logger LOG = LoggerFactory.getLogger(UserDao.class);
    private IDatabaseConnector databaseConnector;

    public UserDao(IDatabaseConnector databaseConnector){
        this.databaseConnector = databaseConnector;
    }


    /**
     * Method retrieves all orders from the Users table
     * @throws UserDaoException in case of SQL exception
     */
    public List<User> getAll() throws UserDaoException {
        List<User> users = new ArrayList<>();
        try{
            ResultSet resultSet = databaseConnector.getStatement().executeQuery(SELECT_ALL_USERS_QUERY);

            while (resultSet.next()){
                User user = new User();

                user.setId(resultSet.getInt("ID"));
                user.setName(resultSet.getString("NAME"));
                user.setPassword(resultSet.getBytes("PASSWORD"));;
                user.setRole(resultSet.getString("ROLE"));

                users.add(user);
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
            throw new UserDaoException(e.getMessage());
        }
        return users;
    }

    /**
     * Method returns User object by ID from the Users table
     * @throws UserDaoException in case of SQL exception
     */
    public User getEntityById(int id) throws UserDaoException {
        User user = new User();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = databaseConnector.getPrepareStatement(SELECT_USER_QUERY);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                user.setId(resultSet.getInt("ID"));
                user.setName(resultSet.getString("NAME"));
                user.setPassword(resultSet.getBytes("PASSWORD"));
                user.setRole(resultSet.getString("ROLE"));
            }

        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
            throw new UserDaoException(e.getMessage());
        } finally {
            if(preparedStatement != null){
                databaseConnector.closePreparedStatement(preparedStatement);
            }
        }
        return user;
    }

    /**
     * Method updates the existing record
     * @throws UserDaoException in case of SQL exception
     */
    public void update(User entity) throws UserDaoException {
        PreparedStatement preparedStatement = null;
        try{
            preparedStatement = databaseConnector.getPrepareStatement(UPDATE_USER_QUERY);
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setBytes(2, entity.getPassword());
            preparedStatement.setString(3, entity.getRole());
            preparedStatement.setInt(4, entity.getId());
            preparedStatement.execute();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
            throw new UserDaoException(e.getMessage());
        }finally {
            if (preparedStatement != null) {
                databaseConnector.closePreparedStatement(preparedStatement);
            }
        }
    }

    /**
     * Method deletes the record by the ID
     * @throws UserDaoException in case of SQL exception
     */
    public void delete(int id) throws UserDaoException {
        PreparedStatement preparedStatement = null;
        try{
            preparedStatement = databaseConnector.getPrepareStatement(DELETE_USER_QUERY);
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
            throw new UserDaoException(e.getMessage());
        }finally {
            if (preparedStatement != null) {
                databaseConnector.closePreparedStatement(preparedStatement);
            }
        }
    }

    /**
     * Method creates new order record
     * @throws UserDaoException in case of SQL exception
     */
    public void create(User entity) throws UserDaoException {
        PreparedStatement preparedStatement = null;
        try{
            preparedStatement = databaseConnector.getPrepareStatement(INSERT_USER_QUERY);
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setBytes(2, entity.getPassword());
            preparedStatement.setString(3, entity.getRole());
            preparedStatement.execute();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
            throw new UserDaoException(e.getMessage());
        }finally {
            if (preparedStatement != null) {
                databaseConnector.closePreparedStatement(preparedStatement);
            }
        }
    }
}
