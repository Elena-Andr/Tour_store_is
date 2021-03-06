package ru.innopolis.tourstore.dao;

import ru.innopolis.tourstore.entity.Tour;
import ru.innopolis.tourstore.exception.TourDaoException;

import java.util.List;

public interface TourDao {

    List<Tour> getAll() throws TourDaoException;
    Tour getEntityById(int id) throws TourDaoException;
    void update(Tour entity) throws TourDaoException;
    void delete(int id) throws TourDaoException;
    void create(Tour entity) throws TourDaoException;
}
