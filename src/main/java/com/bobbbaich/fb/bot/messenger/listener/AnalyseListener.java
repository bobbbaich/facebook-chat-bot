package com.bobbbaich.fb.bot.messenger.listener;

import com.bobbbaich.fb.bot.cache.api.Event;
import com.bobbbaich.fb.bot.model.Message;
import com.bobbbaich.fb.bot.service.api.TweetService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class AnalyseListener {
    private final TweetService tweetService;

    @EventListener
    public void add(Event<Message> event) {
        Message message = event.getEventObj();
        tweetService.collectTweets(message);
        log.debug("++++> Received event for analyse: {}", message);
    }
}
