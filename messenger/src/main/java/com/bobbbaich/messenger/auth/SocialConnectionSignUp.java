package com.bobbbaich.messenger.auth;

import com.bobbbaich.messenger.model.User;
import com.bobbbaich.messenger.model.UserRole;
import com.bobbbaich.messenger.service.api.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.social.connect.web.SignInAdapter;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.TwitterProfile;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Collections;

@Slf4j
@Service
@RequiredArgsConstructor
public final class SocialConnectionSignUp implements ConnectionSignUp {
    private final UserService userService;
    private final SignInAdapter signInAdapter;

    @Override
    public String execute(Connection<?> connection) {
        Assert.notNull(connection, "Param 'connection' cannot be null.");

        Object api = connection.getApi();
        String socialUserId = null;

        if (api instanceof Twitter) {
            socialUserId = signUp((Twitter) api);
        } else if (api instanceof Facebook) {
            socialUserId = signUp((Facebook) api);
        }
        if (socialUserId == null) {
            log.error("User with social profile '{}' was not signed up.", connection.getProfileUrl());
            throw new SocialAuthorizationException("User was not signed up.");
        }

        signInAdapter.signIn(socialUserId, connection, null);
        return socialUserId;
    }

    private String signUp(Twitter api) {
        TwitterProfile userProfile = api.userOperations().getUserProfile();
        String socialUserId = String.valueOf(userProfile.getId());
        User localUser = createLocalUser(socialUserId);
        return localUser == null ? null : socialUserId;
    }

    private String signUp(Facebook api) {
        throw new SocialAuthorizationException("Social sign-up is not implemented for Facebook API.");
    }

    private User createLocalUser(String userId) {
        return userService.create(User.builder()
                .username(userId)
                .password("pass")
                .userRoles(Collections.singleton(UserRole.ROLE_USER))
                .build());
    }
}