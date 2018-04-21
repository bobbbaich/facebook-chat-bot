package com.bobbbaich.fb.bot.messenger.controller;

import com.github.messenger4j.Messenger;
import com.github.messenger4j.webhook.Event;
import com.github.messenger4j.webhook.event.MessageDeliveredEvent;
import com.github.messenger4j.webhook.event.TextMessageEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Slf4j
@RequiredArgsConstructor
@Component
public class MessengerEventHandler {
    private final Messenger messenger;

    public void onEvent(Event event) {
        if (event.isTextMessageEvent()) {
            TextMessageEvent textMessageEvent = event.asTextMessageEvent();
            log.info("Message has been received with text: {}", textMessageEvent.text());
        } else if (event.isMessageDeliveredEvent()) {
            MessageDeliveredEvent messageDeliveredEvent = event.asMessageDeliveredEvent();
            Instant timestamp = messageDeliveredEvent.timestamp();
            log.info("Message has been delivered at timestamp: {}", timestamp);
        } else if (event.isPostbackEvent()){

        }
    }
}