package com.bobbbaich.messenger.processor;

import com.bobbbaich.messenger.processor.api.EventProcessor;
import com.bobbbaich.messenger.service.api.MessageService;
import com.github.messenger4j.webhook.Event;
import com.github.messenger4j.webhook.event.TextMessageEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Slf4j
@RequiredArgsConstructor
@Component
public class TextMessageEventProcessor implements EventProcessor<TextMessageEvent> {
    private final MessageService messageService;

    @Override
    public void doProcessing(TextMessageEvent event) {
        log.debug("TextMessageEvent has been processing...");
        messageService.sendToAnalysis(event.recipientId(), event.text());
    }

    @Override
    public void doProcessing(Event event) {
        TextMessageEvent textMessageEvent = event.asTextMessageEvent();
        Assert.notNull(textMessageEvent, "TextMessageEvent cannot be null!");

        doProcessing(textMessageEvent);
    }
}