package com.bobbbaich.fb.bot.controller;

import com.bobbbaich.fb.bot.service.SocialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.social.oauth1.AuthorizedRequestToken;
import org.springframework.social.oauth1.OAuth1Operations;
import org.springframework.social.oauth1.OAuthToken;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.impl.TwitterTemplate;
import org.springframework.social.twitter.connect.TwitterConnectionFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private SocialService facebookService;

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
