package ru.innopolis.tourstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import ru.innopolis.tourstore.entity.TourEntity;
import ru.innopolis.tourstore.exception.TourDaoException;
import org.springframework.stereotype.Service;
import ru.innopolis.tourstore.repository.TourRepository;

import java.util.List;

@Service
public class TourServiceImpl implements TourService {

    private TourRepository tourRepository;

    @Autowired
    public void setTourRepository(TourRepository tourRepository) {
        this.tourRepository = tourRepository;
    }

    @Override
    public List<TourEntity> getAll() throws TourDaoException{
        return (List)tourRepository.findAll();
    }

    @Override
    public TourEntity getEntityById(int id) throws TourDaoException {
        return tourRepository.findOne(id);
    }

    @Override
    public void update(TourEntity entity) throws TourDaoException {
         tourRepository.saveAndFlush(entity);
    }

    @Override
    public void delete(int id) throws TourDaoException {

        //Mark tour as deleted
        TourEntity tour = tourRepository.getOne(id);
        tour.setDeleted(true);

        tourRepository.saveAndFlush(tour);
    }

    @Override
    public void create(TourEntity entity) throws TourDaoException {
        tourRepository.saveAndFlush(entity);
    }
}


