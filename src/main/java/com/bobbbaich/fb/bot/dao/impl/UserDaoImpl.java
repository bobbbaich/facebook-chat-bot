package com.bobbbaich.fb.bot.dao.impl;

import com.bobbbaich.fb.bot.dao.UserDao;
import com.bobbbaich.fb.bot.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class UserDaoImpl implements UserDao {
    private static final Logger LOG = LoggerFactory.getLogger(UserDaoImpl.class);

    private static final String HQL_SELECT_USER_BY_USERNAME = "SELECT u FROM com.bobbbaich.fb.bot.model.User as u WHERE u.username = :username";

    private SessionFactory sessionFactory;

    @Override
    public Long create(User entity) {
        return (Long) sessionFactory.getCurrentSession().save(entity);
    }

    @Override
    public User read(Long id) {
        return sessionFactory.getCurrentSession().find(User.class, id);
    }

    @Override
    public void update(User entity) {
        sessionFactory.getCurrentSession().update(entity);
    }

    @Override
    public void delete(User entity) {
        sessionFactory.getCurrentSession().delete(entity);
    }

    @Override
    public User findByUsername(String username) {
        Query<User> query = sessionFactory.getCurrentSession().createQuery(HQL_SELECT_USER_BY_USERNAME, User.class);
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
