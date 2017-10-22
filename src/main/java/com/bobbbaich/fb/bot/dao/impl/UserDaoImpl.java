package com.bobbbaich.fb.bot.dao.impl;

import com.bobbbaich.fb.bot.dao.UserDao;
import com.bobbbaich.fb.bot.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class UserDaoImpl implements UserDao {

    private SessionFactory sessionFactory;

    @Override
    public Long create(User entity) {
        return (Long) sessionFactory.getCurrentSession()
                .save(entity);
    }

    @Override
    public User read(Long id) {
        return sessionFactory.getCurrentSession().find(User.class, id);
    }

    @Override
    public void update(User entity) {
        sessionFactory.getCurrentSession()
                .update(entity);
    }

    @Override
    public void delete(User entity) {
        sessionFactory.getCurrentSession().delete(entity);
    }

    @Override
    public User findByUsername(String username) {
        Query<User> query = sessionFactory.getCurrentSession()
                .createQuery("SELECT u FROM user u WHERE u.username = :username", User.class);

        query.setParameter("username", username);

        return query.uniqueResult();
    }

    @Override
    public boolean exists(User user) {
        return true;
    }

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
