package com.bobbbaich.fb.bot.social.facebook;

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
    @Autowired
    private UserRepository userRepository;

    public String execute(Connection<?> connection) {
        Facebook facebook = (Facebook) connection.getApi();
        User userProfile = facebook.userOperations().getUserProfile();
        return signUp(userProfile);
    }

    //TODO: rewrite user insert, make UserService to be responsible for this logic
    private String signUp(User userProfile) {
        String email = userProfile.getEmail();
        com.bobbbaich.fb.bot.model.User newUser = new com.bobbbaich.fb.bot.model.User();

        newUser.setUsername(email);
        newUser.setUserRole(UserRole.ROLE_USER);
        newUser.setPassword("pass");

        userRepository.insert(newUser);
        return email;
    }


}