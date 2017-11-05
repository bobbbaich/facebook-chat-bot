package com.bobbbaich.fb.bot.service.domain;

import com.bobbbaich.fb.bot.model.Opinion;

import java.util.Set;

public interface OpinionService {
    void collectOpinions(Set<Opinion> opinions);
}
