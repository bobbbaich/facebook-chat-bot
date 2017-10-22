package com.bobbbaich.fb.bot.social;

import com.bobbbaich.fb.bot.model.UserRole;
import com.bobbbaich.fb.bot.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.TwitterProfile;
import org.springframework.stereotype.Service;

@Service
public final class SocialConnectionSignUp implements ConnectionSignUp {
    @Autowired
    private UserDao userDao;

    public String execute(Connection<?> connection) {
        //TODO: rewrite method to handle different connection types
        Twitter twitter = (Twitter) connection.getApi();
        TwitterProfile userProfile = twitter.userOperations().getUserProfile();
        return signUp(userProfile);
    }

    //TODO: rewrite user insert, make UserService to be responsible for this logic
    private String signUp(TwitterProfile userProfile) {
        String email = userProfile.getName();
        com.bobbbaich.fb.bot.model.User newUser = new com.bobbbaich.fb.bot.model.User();

        newUser.setUsername(email);
        newUser.setUserRole(UserRole.ROLE_USER);
        newUser.setPassword("pass");

        userDao.create(newUser);
        return email;
    }


}