package ru.innopolis.tourstore.dao.hibernate;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.innopolis.tourstore.dao.TourDao;
import ru.innopolis.tourstore.entity.TourEntity;
import ru.innopolis.tourstore.exception.TourDaoException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class TourDaoImplHibernate implements TourDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public List<TourEntity> getAll() throws TourDaoException {
        List tours = entityManager.createQuery("FROM TourEntity").getResultList();
       // entityManager.close();
        return tours;
    }

    @Override
    @Transactional
    public TourEntity getEntityById(int id) throws TourDaoException {
        TourEntity tour = entityManager.find(TourEntity.class, new Integer(id));
     //  entityManager.close();
        return tour;
    }

    @Override
    @Transactional
    public void update(TourEntity entity) throws TourDaoException {
        entityManager.merge(entity);
      //  entityManager.close();
    }

    @Override
    @Transactional
    public void delete(int id) throws TourDaoException {
        TourEntity tour = entityManager.find(TourEntity.class, new Integer(id));
        entityManager.remove(tour);
     //   entityManager.close();
    }

    @Override
    @Transactional
    public void create(TourEntity entity) throws TourDaoException {
        entityManager.persist(entity);
     //   entityManager.close();
    }
}
