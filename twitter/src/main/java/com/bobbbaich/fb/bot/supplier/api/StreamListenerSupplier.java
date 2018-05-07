package com.bobbbaich.fb.bot.supplier.api;

import org.springframework.social.twitter.api.StreamListener;

public interface StreamListenerSupplier {
    StreamListener supply(String topicName, String recipientId, long streamNumber, Integer limit);
}
