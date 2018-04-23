package com.bobbbaich.fb.bot.service.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class AnalyseServiceImpl implements AnalyseService {
    private final TweetService service;

    @Override
    public void analyse(String text) {
        service.collectTweets(text);
    }
}
