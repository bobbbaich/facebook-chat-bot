package com.bobbbaich.fb.bot.messenger.processor;

import com.github.messenger4j.webhook.Event;
import com.github.messenger4j.webhook.event.TextMessageEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Slf4j
@Component
public class TextMessageEventProcessor implements EventProcessor<TextMessageEvent> {
    @Override
    public void doProcessing(TextMessageEvent event) {
        log.debug("TextMessageEvent has been processing...");
    }

    @Override
    public void doProcessing(Event event) {
        TextMessageEvent textMessageEvent = event.asTextMessageEvent();
        Assert.notNull(textMessageEvent, "TextMessageEvent cannot be null!");

        doProcessing(textMessageEvent);
    }
}