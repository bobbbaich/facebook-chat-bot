package com.bobbbaich.fb.bot.dao.impl;

import com.bobbbaich.fb.bot.model.User;
import com.bobbbaich.fb.bot.dao.UserDao;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao {
    @Override
    public void insert(User user) {

    }

    @Override
    public User findByUsername(String username) {
        return null;
    }

    @Override
    public boolean exists(User user) {
        return false;
    }

    @Override
    public User save(User user) {
        return null;
    }
}
