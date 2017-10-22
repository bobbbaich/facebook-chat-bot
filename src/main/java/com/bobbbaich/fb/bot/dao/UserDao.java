package com.bobbbaich.fb.bot.dao;

import com.bobbbaich.fb.bot.dao.common.GenericDao;
import com.bobbbaich.fb.bot.model.User;

public interface UserDao extends GenericDao<User, Long> {
    User findByUsername(String username);

    boolean exists(User user);
}
