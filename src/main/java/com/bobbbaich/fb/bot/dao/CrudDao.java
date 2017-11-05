package com.bobbbaich.fb.bot.dao;

import java.io.Serializable;

public interface CrudDao<T, PK extends Serializable> {

    PK create(T entity);

    T read(PK id);

    void update(T entity);

    void delete(T entity);
}
