package com.bobbbaich.fb.bot.social;

import com.bobbbaich.fb.bot.model.UserRole;
import com.bobbbaich.fb.bot.service.domain.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.social.connect.web.SignInAdapter;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.TwitterProfile;
import org.springframework.stereotype.Service;

@Service
public final class SocialConnectionSignUp implements ConnectionSignUp {

    private UserService userService;
    private SignInAdapter signInAdapter;

    @Override
    public String execute(Connection<?> connection) {
        Object api = connection.getApi();
        String socialUserId = null;

        if (api instanceof Twitter) {
            socialUserId = signUp((Twitter) api);
        } else if (api instanceof Facebook) {
            socialUserId = signUp((Facebook) api);
        }
        if (socialUserId == null) throw new SocialAuthorizationException("User is not signed up.");

        signInAdapter.signIn(socialUserId, connection, null);
        return socialUserId;
    }

    private String signUp(Twitter api) {
        TwitterProfile userProfile = api.userOperations().getUserProfile();
        String socialUserId = String.valueOf(userProfile.getId());
        Long localUserId = createLocalUser(socialUserId);
        return localUserId == null ? null : socialUserId;
    }

    private String signUp(Facebook api) {
        throw new SocialAuthorizationException("Social sign-up is not implemented for Facebook API.");
    }

    private Long createLocalUser(String userId) {
        com.bobbbaich.fb.bot.model.User newUser = new com.bobbbaich.fb.bot.model.User();

        newUser.setUsername(userId);
        newUser.setUserRole(UserRole.ROLE_USER);
//        TODO: create password for social user
        newUser.setPassword("pass");
        return userService.create(newUser);
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setSignInAdapter(SignInAdapter signInAdapter) {
        this.signInAdapter = signInAdapter;
    }
}