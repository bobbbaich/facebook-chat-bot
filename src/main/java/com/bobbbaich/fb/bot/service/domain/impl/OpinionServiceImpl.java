package com.bobbbaich.fb.bot.service.domain.impl;

import com.bobbbaich.fb.bot.dao.BatchDao;
import com.bobbbaich.fb.bot.model.Opinion;
import com.bobbbaich.fb.bot.service.domain.OpinionService;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class OpinionServiceImpl implements OpinionService {

    private BatchDao<Opinion, Long> opinionBatchDao;

    @Override
    public void collectOpinions(Set<Opinion> opinions) {
        opinionBatchDao.insertBatch(opinions);
    }
}
