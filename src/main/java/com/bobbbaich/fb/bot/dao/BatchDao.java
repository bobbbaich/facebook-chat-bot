package com.bobbbaich.fb.bot.dao;

import java.io.Serializable;
import java.util.Collection;

public interface BatchDao<T, PK extends Serializable> {
    void insertBatch(Collection<T> batch);
}
