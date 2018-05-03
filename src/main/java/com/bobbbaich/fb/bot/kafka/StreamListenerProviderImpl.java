package com.bobbbaich.fb.bot.kafka;

import com.bobbbaich.fb.bot.cache.api.EventPublisher;
import com.bobbbaich.fb.bot.kafka.api.Producer;
import com.bobbbaich.fb.bot.kafka.api.StreamListenerProvider;
import com.bobbbaich.fb.bot.model.TweetMessage;
import com.google.gson.Gson;
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
    public StreamListener provide(final String topic, final String recipientId, final long streamNumber, final Integer limit) {
        return new StreamListener() {
            private Gson mapper = new Gson();
            private int counter;
            private boolean sentStopEvent = false;

            @Override
            public void onTweet(Tweet tweet) {
                if (counter < limit) {
                    log.debug("Tweet = {}", tweet.getText());
                    producer.send(topic, mapper.toJson(TweetMessage.builder()
                            .recipientId(recipientId)
                            .streamNumber(streamNumber)
                            .text(tweet.getText())
                            .build()
                    ));
                } else {
                    if (!sentStopEvent) {
                        log.debug("Try create CloseStreamEvent");
                        sentStopEvent = true;
                        publisher.close(recipientId, streamNumber);
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
