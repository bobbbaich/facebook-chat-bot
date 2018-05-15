package com.bobbbaich.messenger.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.web.SignInAdapter;
import org.springframework.social.security.SocialAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.NativeWebRequest;

@Service
public class SocialSignInAdapter implements SignInAdapter {

    private UserDetailsService userDetailsService;

    @Override
    public String signIn(String localUsername, Connection<?> connection, NativeWebRequest request) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(localUsername);
        if (userDetails == null)
            throw new SocialAuthorizationException("Local user was not found. Authorization can not be performed.");

        SocialAuthenticationToken authentication = new SocialAuthenticationToken(
                connection, userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return null;
    }

    @Autowired
    public void setUserDetailsService(@Qualifier("userServiceImpl") UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }
}
