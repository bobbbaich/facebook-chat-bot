package com.bobbbaich.fb.bot.dao.impl;

import com.bobbbaich.fb.bot.dao.BatchDao;
import com.bobbbaich.fb.bot.dao.CrudDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Collection;

@Transactional
public class BatchDaoImpl<T, PK extends Serializable> implements BatchDao<T, PK> {

    private SessionFactory sessionFactory;
    private CrudDao<T, PK> crudDao;

    public BatchDaoImpl() {
    }

    public BatchDaoImpl(SessionFactory sessionFactory, CrudDao<T, PK> crudDao) {
        this.crudDao = crudDao;
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void insertBatch(Collection<T> batch) {
        for (T entity : batch) {
            crudDao.create(entity);
        }
    }
}
