package ru.innopolis.tourstore.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.innopolis.tourstore.dao.TourDao;
import ru.innopolis.tourstore.dao.TourDaoImpl;
import ru.innopolis.tourstore.entity.Tour;
import ru.innopolis.tourstore.exception.TourDaoException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TourServiceImpl implements TourService {
    private static final Logger LOG = LoggerFactory.getLogger(TourServiceImpl.class);

    @Autowired
    private TourDao tourDao;

    @Override
    public List<Tour> getAll() throws TourDaoException{
        return tourDao.getAll();
    }

    @Override
    public Tour getEntityById(int id) throws TourDaoException {
        return tourDao.getEntityById(id);
    }

    @Override
    public void update(Tour entity) throws TourDaoException {
        tourDao.update(entity);
    }

    @Override
    public void delete(int id) throws TourDaoException {
        tourDao.delete(id);
    }

    @Override
    public void create(Tour entity) throws TourDaoException {
        tourDao.create(entity);
    }
}


