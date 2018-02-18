package com.bobbbaich.fb.bot.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.social.twitter.api.StreamingOperations;
import org.springframework.social.twitter.api.Twitter;

@Slf4j
@Configuration
public class TwitterConfig {

    @Bean
    @Scope(value = "request", proxyMode = ScopedProxyMode.INTERFACES)
    public StreamingOperations streamingOperations(Twitter twitter) {
        return twitter.streamingOperations();
    }
}
