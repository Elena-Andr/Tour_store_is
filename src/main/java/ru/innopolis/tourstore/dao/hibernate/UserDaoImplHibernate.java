package ru.innopolis.tourstore.dao.hibernate;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.innopolis.tourstore.dao.UserDao;
import ru.innopolis.tourstore.entity.UserEntity;
import ru.innopolis.tourstore.exception.UserDaoException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
@Transactional
public class UserDaoImplHibernate implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<UserEntity> getAll()  {
        List users = entityManager.createQuery("FROM UserEntity").getResultList();
        return users;
    }

    @Override
    public UserEntity getEntityById(int id)  {
        UserEntity user = entityManager.find(UserEntity.class, new Integer(id));
        return user;
    }

    @Override
    public UserEntity getEntityByName(String name)  {
        String queryString = "SELECT * FROM USERS WHERE NAME=?";
        Query query = entityManager.createNativeQuery(queryString, UserEntity.class);
        List<UserEntity> result = query.setParameter(1, name).getResultList();
        return result.get(0);
    }

    @Override
    public void update(UserEntity entity) {
        entityManager.merge(entity);
    }

    @Override
    public void delete(int id) {
        UserEntity user = entityManager.find(UserEntity.class, new Integer(id));
        entityManager.remove(user);
    }

    @Override
    public void create(UserEntity entity) {
        entityManager.persist(entity);
        entityManager.flush();
    }
}
