package com.bobbbaich.fb.bot.service.domain.impl;

import com.bobbbaich.fb.bot.dao.CrudDao;
import com.bobbbaich.fb.bot.model.Fact;
import com.bobbbaich.fb.bot.service.domain.FactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FactServiceImpl implements FactService {
    private CrudDao<Fact, Long> factCrudDao;

    @Override
    public Long create(Fact fact) {
        return factCrudDao.create(fact);
    }

    @Autowired
    public void setFactCrudDao(CrudDao<Fact, Long> factCrudDao) {
        this.factCrudDao = factCrudDao;
    }
}
