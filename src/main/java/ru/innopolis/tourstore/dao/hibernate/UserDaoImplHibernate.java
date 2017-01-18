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
public class UserDaoImplHibernate implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public List<UserEntity> getAll() throws UserDaoException {
        List users = entityManager.createQuery("FROM UserEntity").getResultList();
        //entityManager.close();
        return users;
    }

    @Override
    @Transactional
    public UserEntity getEntityById(int id) throws UserDaoException {
        UserEntity user = entityManager.find(UserEntity.class, new Integer(id));
        //entityManager.close();
        return user;
    }

    @Override
    @Transactional
    public UserEntity getEntityByName(String name) throws UserDaoException {
//        UserEntity user = (UserEntity) entityManager
//                .createQuery("FROM UserEntity")
//                .setParameter("name", name)
//                .getSingleResult();

//        TypedQuery<UserEntity> tq = entityManager.createQuery("from UserEntity WHERE name=?", UserEntity.class);
//        UserEntity result = tq.setParameter(1, name).getSingleResult();

        String queryString = "SELECT * FROM USERS WHERE NAME=?";
        Query query = entityManager.createNativeQuery(queryString, UserEntity.class);
        List<UserEntity> result = query.setParameter(1, name).getResultList();

       // entityManager.close();

        return result.get(0);
    }

    @Override
    @Transactional
    public void update(UserEntity entity) throws UserDaoException {
        entityManager.merge(entity);
       // entityManager.close();
    }

    @Override
    @Transactional
    public void delete(int id) throws UserDaoException {
        UserEntity user = entityManager.find(UserEntity.class, new Integer(id));
        entityManager.remove(user);
     //   entityManager.close();
    }

    @Override
    @Transactional
    public void create(UserEntity entity) throws UserDaoException {
        entityManager.persist(entity);
     //   entityManager.close();
    }
}
