package ru.innopolis.tourstore.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.innopolis.tourstore.db.IDatabaseConnector;
import ru.innopolis.tourstore.entity.Tour;
import org.springframework.stereotype.Repository;
import ru.innopolis.tourstore.exception.TourDaoException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static ru.innopolis.tourstore.db.SQLConstants.*;

/**
 * Object represents DAO for tour entity
 */
@Repository
public class TourDaoImpl implements TourDao {
    private static final Logger LOG = LoggerFactory.getLogger(TourDaoImpl.class);

    @Autowired
    private IDatabaseConnector databaseConnector;

    /**
     * Methods retrieves all orders from the Tours table
     * @throws TourDaoException in case of SQL exception
     */
    @Override
    public List<Tour> getAll() throws TourDaoException {
        List<Tour> tours = new ArrayList<>();
        try{
            ResultSet resultSet = databaseConnector.getStatement().executeQuery(SELECT_ALL_TOURS_QUERY);

            while (resultSet.next()){
                Tour tour = new Tour();

                tour.setId(resultSet.getInt("ID"));
                tour.setName(resultSet.getString("NAME"));
                tour.setDescription(resultSet.getString("DESCRIPTION"));
                tour.setDeleted(resultSet.getBoolean("DELETED"));

                tours.add(tour);
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
            throw new TourDaoException(e.getMessage());
        }
        return tours;
    }

    /**
     * Methods returns Tour object by ID from the Tours table
     * @throws TourDaoException in case of SQL exception
     */
    @Override
    public Tour getEntityById(int id) throws TourDaoException {
        Tour tour = new Tour();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = databaseConnector.getPrepareStatement(SELECT_TOUR_QUERY);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                tour.setId(resultSet.getInt("ID"));
                tour.setName(resultSet.getString("NAME"));
                tour.setDescription(resultSet.getString("DESCRIPTION"));
                tour.setDeleted(resultSet.getBoolean("DELETED"));
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
            throw new TourDaoException(e.getMessage());
        } finally {
            if(preparedStatement != null){
                databaseConnector.closePreparedStatement(preparedStatement);
            }
        }
        return tour;
    }

    /**
     * Methods update the existing record
     * @throws TourDaoException in case of SQL exception
     */
    @Override
    public void update(Tour entity) throws TourDaoException {
        PreparedStatement preparedStatement = null;
        try{
            preparedStatement = databaseConnector.getPrepareStatement(UPDATE_TOUR_QUERY);
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setString(2, entity.getDescription());
            preparedStatement.setBoolean(3, entity.isDeleted());
            preparedStatement.setInt(4, entity.getId());

            preparedStatement.execute();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
            throw new TourDaoException(e.getMessage());
        }finally {
            if (preparedStatement != null) {
                databaseConnector.closePreparedStatement(preparedStatement);
            }
        }
    }

    /**
     * Method deletes the record by the ID
     * @throws TourDaoException in case of SQL exception
     */
    @Override
    public void delete(int id) throws TourDaoException {
        PreparedStatement preparedStatement = null;
        try{
            preparedStatement = databaseConnector.getPrepareStatement(DELETE_TOUR_QUERY);
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
            throw new TourDaoException(e.getMessage());
        }finally {
            if (preparedStatement != null) {
                databaseConnector.closePreparedStatement(preparedStatement);
            }
        }

    }

    /**
     * Method creates new order record
     * @throws TourDaoException in case of SQL exception
     */
    @Override
    public void create(Tour entity) throws TourDaoException {
        PreparedStatement preparedStatement = null;
        try{
            preparedStatement = databaseConnector.getPrepareStatement(INSERT_TOUR_QUERY);
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setString(2, entity.getDescription());
            preparedStatement.setBoolean(3, entity.isDeleted());
            preparedStatement.execute();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
            throw new TourDaoException(e.getMessage());
        }finally {
            if (preparedStatement != null) {
                databaseConnector.closePreparedStatement(preparedStatement);
            }
        }
    }
}
