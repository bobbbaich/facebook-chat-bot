package com.bobbbaich.fb.bot.controller;

import com.bobbbaich.fb.bot.service.api.TweetService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class TestController {

    private final TweetService tweetService;

    @GetMapping("/isAuth")
    public String isAuth() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return String.valueOf(authentication != null);
    }

    @GetMapping("/kafka/send/{keyWord}/{limit}")
    public String trackByKeyWord(
            @Value("${kafka.topic.tweet:test}") String tweetTopicName,
            @PathVariable String keyWord,
            @PathVariable Integer limit) {
        tweetService.collectTweets(tweetTopicName, keyWord, limit);
        return "Test message sent.";
    }
}