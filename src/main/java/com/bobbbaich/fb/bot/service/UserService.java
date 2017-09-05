package com.bobbbaich.fb.bot.service;

public interface UserService {

    /**
     * Useful to create user on first interaction with messenger.com
     *
     * @param facebookId - value of fb-user id
     * @return ID of created entity in inner DB
     */
    String create(String facebookId);
}
