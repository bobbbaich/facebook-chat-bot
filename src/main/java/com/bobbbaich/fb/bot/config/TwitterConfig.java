package com.bobbbaich.fb.bot.config;

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

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
@Configuration
public class TwitterConfig {
    @Value("${timeout.thread.pool:1}")
    private int timeoutThreads;

    @Bean
    @Scope(value = "request", proxyMode = ScopedProxyMode.INTERFACES)
    public StreamingOperations streamingOperations(Twitter twitter) {
        return twitter.streamingOperations();
    }

    @Bean
    @Scope(value = "request", proxyMode = ScopedProxyMode.INTERFACES)
    public UserOperations userOperations(Twitter twitter) {
        return twitter.userOperations();
    }

    @Bean
    public ExecutorService executorService() {
        return Executors.newFixedThreadPool(timeoutThreads);
    }

//    @Bean
//    TwitterTemplate getTwtTemplate(){
//        return new TwitterTemplate(consumerKey, consumerSecret, accessToken, accessTokenSecret);
//    }
}
