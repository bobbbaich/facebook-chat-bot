package com.bobbbaich.fb.bot.dao;

import com.bobbbaich.fb.bot.model.User;

public interface UserDao {
    User findByUsername(String username);
}
