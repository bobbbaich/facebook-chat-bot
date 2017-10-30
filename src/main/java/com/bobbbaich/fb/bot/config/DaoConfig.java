package com.bobbbaich.fb.bot.config;

import com.bobbbaich.fb.bot.dao.common.CrudDao;
import com.bobbbaich.fb.bot.dao.impl.CrudDaoImpl;
import com.bobbbaich.fb.bot.model.Conclusion;
import com.bobbbaich.fb.bot.model.Fact;
import com.bobbbaich.fb.bot.model.Opinion;
import com.bobbbaich.fb.bot.model.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DaoConfig {
    @Bean
    public CrudDao<User, Long> userDao() {
        return new CrudDaoImpl<>(User.class);
    }

    @Bean
    public CrudDao<Fact, Long> factDao() {
        return new CrudDaoImpl<>(Fact.class);
    }

    @Bean
    public CrudDao<Conclusion, Long> conclusionDao() {
        return new CrudDaoImpl<>(Conclusion.class);
    }

    @Bean
    public CrudDao<Opinion, Long> opinionDao() {
        return new CrudDaoImpl<>(Opinion.class);
    }
}
