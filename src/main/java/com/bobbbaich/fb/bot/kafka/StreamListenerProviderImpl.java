package com.bobbbaich.fb.bot.kafka;

import com.bobbbaich.fb.bot.kafka.api.BrokerProducer;
import com.bobbbaich.fb.bot.kafka.api.StreamListenerProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.social.twitter.api.*;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class StreamListenerProviderImpl implements StreamListenerProvider {
    private final BrokerProducer<String> producer;

    @Override
    public StreamListener provide(String topicName, String keyWord, Integer limit) {
        return new StreamListener() {
            private String word = keyWord;
            private Integer limitMassage = limit;
            private int counter;

            @Override
            public void onTweet(Tweet tweet) {

                producer.send(topicName, tweet.getText());
                counter++;
//                if ()
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
