package com.bobbbaich.fb.bot.dao.impl;

import com.bobbbaich.fb.bot.dao.CrudDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

@Transactional
public class CrudDaoImpl<T, PK extends Serializable> implements CrudDao<T, PK> {

    private Class<T> type;
    private SessionFactory sessionFactory;

    public CrudDaoImpl() {
    }

    public CrudDaoImpl(SessionFactory sessionFactory, Class<T> type) {
        this.sessionFactory = sessionFactory;
        this.type = type;
    }

    @Override
    @SuppressWarnings("unchecked")
    public PK create(T o) {
        return (PK) getSession().save(o);
    }

    @Override
    public T read(PK id) {
        return getSession().get(type, id);
    }

    @Override
    public void update(T o) {
        getSession().update(o);
    }

    @Override
    public void delete(T o) {
        getSession().delete(o);
    }

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }
}
