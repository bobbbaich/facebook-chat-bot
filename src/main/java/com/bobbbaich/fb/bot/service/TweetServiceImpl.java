package com.bobbbaich.fb.bot.service;


import com.bobbbaich.fb.bot.kafka.api.StreamOperationsService;
import com.bobbbaich.fb.bot.service.api.TweetService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.social.twitter.api.Stream;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class TweetServiceImpl implements TweetService {
    private final StreamOperationsService streamOperationsService;

    @Override
    public void collectTweets(String topicName, String keyWord, Integer limit) {
        Stream stream = streamOperationsService.runStream(topicName, keyWord, limit);
        log.debug("Stream {}", stream);
    }
}
