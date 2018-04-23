package com.bobbbaich.fb.bot.kafka;

import com.bobbbaich.fb.bot.cache.api.EventPublisher;
import com.bobbbaich.fb.bot.kafka.api.Producer;
import com.bobbbaich.fb.bot.kafka.api.StreamListenerProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.social.twitter.api.*;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class StreamListenerProviderImpl implements StreamListenerProvider {
    private final Producer<String> producer;
    private final EventPublisher<Stream> publisher;

    @Override
    public StreamListener provide(final String topic, final String keyWord, final Integer limit) {
        return new StreamListener() {
            private int counter;
            private boolean sentStopEvent = false;

            @Override
            public void onTweet(Tweet tweet) {
                if (counter < limit) {
                    producer.send(topic, tweet.getText());
                } else {
                    if (!sentStopEvent) {
                        log.debug("Try create CloseStreamEvent");
                        sentStopEvent = true;
                        publisher.close(topic, keyWord);
                    }
                }
                counter++;
            }

            @Override
            public void onDelete(StreamDeleteEvent streamDeleteEvent) {

            }

            @Override
            public void onLimit(int i) {
                log.debug("Limit tweets was corrupted = {}", i);
            }

            @Override
            public void onWarning(StreamWarningEvent streamWarningEvent) {

            }
        };
    }
}
