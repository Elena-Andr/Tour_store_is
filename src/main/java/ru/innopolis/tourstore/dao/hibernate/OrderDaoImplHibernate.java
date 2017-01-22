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
@Transactional
public class OrderDaoImplHibernate implements OrderDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<OrderEntity> getAll(){
        List orders = entityManager.createQuery("FROM OrderEntity").getResultList();
        return orders;
    }

    @Override
    public OrderEntity getEntityById(int id){
        OrderEntity order = entityManager.find(OrderEntity.class, new Integer(id));
        return order;
    }

    @Override
    public void update(OrderEntity entity) {
        entityManager.merge(entity);
    }

    @Override
    public void delete(int id){
        OrderEntity order = entityManager.find(OrderEntity.class, new Integer(id));
        entityManager.remove(order);
    }

    @Override
    public void create(OrderEntity entity){
        entityManager.persist(entity);
        entityManager.flush();
    }
}
