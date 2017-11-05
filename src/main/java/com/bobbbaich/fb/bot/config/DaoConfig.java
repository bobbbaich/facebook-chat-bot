package com.bobbbaich.fb.bot.config;

import com.bobbbaich.fb.bot.dao.BatchDao;
import com.bobbbaich.fb.bot.dao.CrudDao;
import com.bobbbaich.fb.bot.dao.impl.BatchDaoImpl;
import com.bobbbaich.fb.bot.dao.impl.CrudDaoImpl;
import com.bobbbaich.fb.bot.model.Conclusion;
import com.bobbbaich.fb.bot.model.Fact;
import com.bobbbaich.fb.bot.model.Opinion;
import com.bobbbaich.fb.bot.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DaoConfig {
    private SessionFactory sessionFactory;

    //  CRUD DAO beans
    @Bean
    public CrudDao<User, Long> userDao() {
        return new CrudDaoImpl<>(sessionFactory, User.class);
    }

    @Bean
    public CrudDao<Fact, Long> factDao() {
        return new CrudDaoImpl<>(sessionFactory, Fact.class);
    }

    @Bean
    public CrudDao<Conclusion, Long> conclusionDao() {
        return new CrudDaoImpl<>(sessionFactory, Conclusion.class);
    }

    @Bean
    public CrudDao<Opinion, Long> opinionDao() {
        return new CrudDaoImpl<>(sessionFactory, Opinion.class);
    }

    //  Batch DAO beans
    @Bean
    public BatchDao<User, Long> userBatchDao(CrudDao<User, Long> crudDao) {
        return new BatchDaoImpl<>(sessionFactory, crudDao);
    }

    @Bean
    public BatchDao<Opinion, Long> opinionBatchDao(CrudDao<Opinion, Long> crudDao) {
        return new BatchDaoImpl<>(sessionFactory, crudDao);
    }

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
