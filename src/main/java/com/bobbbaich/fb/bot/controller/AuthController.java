package com.bobbbaich.fb.bot.controller;

import com.bobbbaich.fb.bot.service.FacebookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.impl.FacebookTemplate;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.OAuth2Operations;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private FacebookService facebookService;

    @GetMapping("/user")
    public Object user(OAuth2Authentication auth) {
        if (auth != null) {
            Authentication userAuthentication = auth.getUserAuthentication();
            return userAuthentication.getDetails();
        } else {
            return null;
        }
    }

    @GetMapping("/test")
    public String user() {
        facebookService.test();
        return "method done!";
    }
}
