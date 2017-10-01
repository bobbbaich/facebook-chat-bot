package com.bobbbaich.fb.bot.social.facebook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.web.SignInAdapter;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.NativeWebRequest;

@Service
public class FacebookSignInAdapter implements SignInAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    public String signIn(String localUserId, Connection<?> connection, NativeWebRequest request) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(localUserId);
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails,
                userDetails.getPassword(), userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return null;
    }
}
