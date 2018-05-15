package com.bobbbaich.twitter.listener;


import com.bobbbaich.kafka.model.TweetMessage;
import com.bobbbaich.kafka.producer.api.Producer;
import com.bobbbaich.twitter.cache.api.EventPublisher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.social.twitter.api.*;

@Slf4j
public class TwitterStreamListener implements StreamListener {
    private int counter;
    private boolean sentStopEvent = false;
    private int limit;
    private long streamNumber;
    private String topic;
    private String recipientId;
    private Producer<TweetMessage> producer;
    private EventPublisher<Stream> publisher;

    public TwitterStreamListener(Producer<TweetMessage> producer, EventPublisher<Stream> publisher, String recipientId, String topic, int limit, long streamNumber) {
        this.limit = limit;
        this.streamNumber = streamNumber;
        this.topic = topic;
        this.recipientId = recipientId;
        this.producer = producer;
        this.publisher = publisher;
        log.debug("TwitterStreamListener created");
    }

    @Override
    public void onTweet(Tweet tweet) {
        if (counter < limit) {
            log.debug("Tweet = {}", tweet.getText());
            producer.send(topic, TweetMessage.builder().recipientId(recipientId).streamNumber(streamNumber).text(tweet.getText()).build());
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
