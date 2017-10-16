package com.bobbbaich.fb.bot.dao;

import com.bobbbaich.fb.bot.model.User;

public interface UserDao {

    void insert(User user);

    User findByUsername(String username);

    boolean exists(User user);

    User save(User user);
}
