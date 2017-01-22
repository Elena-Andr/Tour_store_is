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
@Transactional
public class TourDaoImplHibernate implements TourDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<TourEntity> getAll(){
        List tours = entityManager.createQuery("FROM TourEntity").getResultList();
        return tours;
    }

    @Override
    public TourEntity getEntityById(int id) {
        TourEntity tour = entityManager.find(TourEntity.class, new Integer(id));
        return tour;
    }

    @Override
    public void update(TourEntity entity)  {
        entityManager.merge(entity);
    }

    @Override
    public void delete(int id){
        TourEntity tour = entityManager.find(TourEntity.class, new Integer(id));
        entityManager.remove(tour);
    }

    @Override
    public void create(TourEntity entity) {
        entityManager.persist(entity);
        entityManager.flush();
    }
}
