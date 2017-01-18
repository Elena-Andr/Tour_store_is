package ru.innopolis.tourstore.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import ru.innopolis.tourstore.dao.TourDao;
import ru.innopolis.tourstore.entity.TourEntity;
import ru.innopolis.tourstore.exception.TourDaoException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TourServiceImpl implements TourService {

    private TourDao tourDao;

    @Autowired
    public TourServiceImpl(@Qualifier("tourDaoImplHibernate")TourDao tourDao){
        this.tourDao = tourDao;
    }

    @Override
    public List<TourEntity> getAll() throws TourDaoException{
        return tourDao.getAll();
    }

    @Override
    public TourEntity getEntityById(int id) throws TourDaoException {
        return tourDao.getEntityById(id);
    }

    @Override
    public void update(TourEntity entity) throws TourDaoException {
        tourDao.update(entity);
    }

    @Override
    public void delete(int id) throws TourDaoException {
        tourDao.delete(id);
    }

    @Override
    public void create(TourEntity entity) throws TourDaoException {
        tourDao.create(entity);
    }
}


