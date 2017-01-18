package ru.innopolis.tourstore.dao.hibernate;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.innopolis.tourstore.dao.OrderDao;
import ru.innopolis.tourstore.entity.OrderEntity;
import ru.innopolis.tourstore.exception.OrderDaoException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class OrderDaoImplHibernate implements OrderDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public List<OrderEntity> getAll() throws OrderDaoException {
        List orders = entityManager.createQuery("FROM OrderEntity").getResultList();
       // entityManager.close();
        return orders;
    }

    @Override
    @Transactional
    public OrderEntity getEntityById(int id) throws OrderDaoException {
        OrderEntity order = entityManager.find(OrderEntity.class, new Integer(id));
       // entityManager.close();
        return order;
    }

    @Override
    @Transactional
    public void update(OrderEntity entity) throws OrderDaoException {
        entityManager.merge(entity);
      //  entityManager.close();
    }

    @Override
    @Transactional
    public void delete(int id) throws OrderDaoException {
        OrderEntity order = entityManager.find(OrderEntity.class, new Integer(id));
        entityManager.remove(order);
    //    entityManager.close();
    }

    @Override
    @Transactional
    public void create(OrderEntity entity) throws OrderDaoException {
        entityManager.persist(entity);
     //   entityManager.close();
    }
}
