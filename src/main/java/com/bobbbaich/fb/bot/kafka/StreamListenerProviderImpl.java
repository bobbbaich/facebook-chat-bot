package com.bobbbaich.fb.bot.kafka;

import com.bobbbaich.fb.bot.cache.api.StreamCacheEventPublisher;
import com.bobbbaich.fb.bot.kafka.api.BrokerProducer;
import com.bobbbaich.fb.bot.kafka.api.StreamListenerProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.social.twitter.api.StreamDeleteEvent;
import org.springframework.social.twitter.api.StreamListener;
import org.springframework.social.twitter.api.StreamWarningEvent;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class StreamListenerProviderImpl implements StreamListenerProvider {
    private final BrokerProducer<String> producer;
    private final StreamCacheEventPublisher publisher;

    @Override
    public StreamListener provide(String topicName, String keyWord, Integer limit) {
        return new StreamListener() {
            private String topic = topicName;
            private String word = keyWord;
            private Integer limitMassage = limit;
            private int counter;
            private boolean sentStopEvent = false;

            @Override
            public void onTweet(Tweet tweet) {
                if (counter < limitMassage) {
                    producer.send(topicName, tweet.getText());
                } else {
                    if (!sentStopEvent) {
                        log.debug("Try create CloseStreamEvent");
                        sentStopEvent = true;
                        publisher.publishClose(topic, word);
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
