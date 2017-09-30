package com.bobbbaich.fb.bot.social;

import com.bobbbaich.fb.bot.model.UserRole;
import com.bobbbaich.fb.bot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.User;
import org.springframework.stereotype.Service;

@Service
public final class FacebookConnectionSignup implements ConnectionSignUp {
    private String[] fields = {"id", "email", "first_name", "last_name"};

    @Autowired
    private UserRepository userRepository;

    public String execute(Connection<?> connection) {
        Facebook facebook = (Facebook) connection.getApi();
        User userProfile = facebook.fetchObject("me", User.class, fields);
        String email = userProfile.getEmail();

        com.bobbbaich.fb.bot.model.User newUser = new com.bobbbaich.fb.bot.model.User();

        newUser.setUsername(email);
        newUser.setUserRole(UserRole.ROLE_USER);
        newUser.setPassword("pass");

        userRepository.insert(newUser);

        return email;
    }

}