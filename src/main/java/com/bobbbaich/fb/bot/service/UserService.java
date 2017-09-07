package com.bobbbaich.fb.bot.service;

import com.bobbbaich.fb.bot.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    /**
     * Useful to create user on first interaction with messenger.com
     *
     * @param facebookId - value of fb-user id
     * @return ID of created entity in inner DB
     */
    String create(String facebookId);

    String register(User user);
}
