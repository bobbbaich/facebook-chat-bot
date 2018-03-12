package com.bobbbaich.fb.bot.service.impl;

import com.bobbbaich.fb.bot.listeners.ListenerSupplier;
import com.bobbbaich.fb.bot.service.TwitterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.social.twitter.api.StreamListener;
import org.springframework.social.twitter.api.StreamingOperations;
import org.springframework.social.twitter.api.UserOperations;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class TwitterServiceImpl implements TwitterService {
    private final StreamingOperations streamingOperations;
    private final UserOperations userOperations;
    private final ListenerSupplier listenerSupplier;

    @Value("${timeout.time:1000}")
    private int time;

    @Override
    public void run(String keyWord, String... otherWord) throws InterruptedException {
        log.debug("Stream running...");
        List<StreamListener> streamListeners = Collections.singletonList(listenerSupplier.supplyTwitterListener());
        streamingOperations.filter(keyWord, streamListeners);
    }


    @Override
    public void test() {
        log.debug("User profile id = {}", userOperations.getUserProfile().getId());
        log.debug("User profile url = {}", userOperations.getUserProfile().getProfileUrl());
        log.debug("User profile create date = {}", userOperations.getUserProfile().getCreatedDate());
        log.debug("User profile location = {}", userOperations.getUserProfile().getLocation());
        log.debug("User profile followers count = {}", userOperations.getUserProfile().getFollowersCount());
        log.debug("User profile name = {}", userOperations.getUserProfile().getName());
        log.debug("User profile screen name = {}", userOperations.getUserProfile().getScreenName());
        log.debug("User profile getLanguage = {}", userOperations.getUserProfile().getLanguage());
        log.debug("User profile isGeoEnabled = {}", userOperations.getUserProfile().isGeoEnabled());
//        AccountSettingsData accountSettingsData = new AccountSettingsData();
//        accountSettingsData.withSleepTimeEnabled()
//        log.debug("User profile isGeoEnabled = {}", userOperations.updateAccountSettings());
    }

    @Override
    public void runKafka(String keyWord) {
        log.debug("Stream kafka running...");
        List<StreamListener> streamListeners = Collections.singletonList(listenerSupplier.supplyKafkaTwitterListener());
        streamingOperations.filter(keyWord, streamListeners);
    }
}