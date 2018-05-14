package com.bobbbaich.twitter.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.social.twitter.api.StreamingOperations;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.UserOperations;

@Slf4j
@Configuration
public class TwitterConfig {
    @Bean
//    @Scope(value = "request", proxyMode = ScopedProxyMode.INTERFACES)
    public StreamingOperations streamingOperations(Twitter twitter) {
        return twitter.streamingOperations();
    }

    @Bean
//    @Scope(value = "request", proxyMode = ScopedProxyMode.INTERFACES)
    public UserOperations userOperations(Twitter twitter) {
        return twitter.userOperations();
    }
}
