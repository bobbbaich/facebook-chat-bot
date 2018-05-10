package com.bobbbaich.twitter.listener;

import com.bobbbaich.twitter.cache.api.EventPublisher;
import com.bobbbaich.twitter.model.TweetMessage;
import com.bobbbaich.twitter.producer.api.Producer;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.social.twitter.api.*;

@Slf4j
public class TwitterStreamListener implements StreamListener {
    private Gson mapper = new Gson();
    private int counter;
    private boolean sentStopEvent = false;
    private int limit;
    private long streamNumber;
    private String topic;
    private String recipientId;
    private Producer<String> producer;
    private EventPublisher<Stream> publisher;

    public TwitterStreamListener(Producer<String> producer, EventPublisher<Stream> publisher, String recipientId, String topic, int limit, long streamNumber) {
        this.limit = limit;
        this.streamNumber = streamNumber;
        this.topic = topic;
        this.recipientId = recipientId;
        this.producer = producer;
        this.publisher = publisher;
    }

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
}
