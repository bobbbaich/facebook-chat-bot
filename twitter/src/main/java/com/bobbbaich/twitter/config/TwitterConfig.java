package com.bobbbaich.twitter.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.social.twitter.api.StreamingOperations;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.UserOperations;
import org.springframework.social.twitter.api.impl.TwitterTemplate;

@Slf4j
@Configuration
public class TwitterConfig {
    @Value("${spring.social.twitter.app-id}")
    private String twitterId;
    @Value("${spring.social.twitter.app-secret}")
    private String twitterSecret;
    @Value("${spring.social.twitter.token-id}")
    private String twitterTokenId;
    @Value("${spring.social.twitter.token-secret}")
    private String twitterTokenSecret;
    @Bean
    public StreamingOperations streamingOperations() {
        return twitter().streamingOperations();
    }

    @Bean
    public UserOperations userOperations() {
        return twitter().userOperations();
    }

    @Bean
    public Twitter twitter(){
        return new TwitterTemplate(twitterId, twitterSecret, twitterTokenId, twitterTokenSecret);
    }
}
