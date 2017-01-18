package ru.innopolis.tourstore.service;

import ru.innopolis.tourstore.entity.TourEntity;
import ru.innopolis.tourstore.exception.TourDaoException;

import java.util.List;

public interface TourService {
    List<TourEntity> getAll() throws TourDaoException;
    TourEntity getEntityById(int id) throws TourDaoException;
    void update(TourEntity entity) throws TourDaoException;
    void delete(int id) throws TourDaoException;
    void create(TourEntity entity) throws TourDaoException;
}
