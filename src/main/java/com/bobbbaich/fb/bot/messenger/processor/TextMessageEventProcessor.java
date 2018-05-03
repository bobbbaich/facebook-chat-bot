package com.bobbbaich.fb.bot.messenger.processor;

import com.bobbbaich.fb.bot.messenger.service.AnalyseService;
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
    private final AnalyseService analyseService;

    @Override
    public void doProcessing(TextMessageEvent event) {
        log.debug("TextMessageEvent has been processing...");
        analyseService.startAnalyse(event.recipientId(), event.text());
    }

    @Override
    public void doProcessing(Event event) {
        TextMessageEvent textMessageEvent = event.asTextMessageEvent();
        Assert.notNull(textMessageEvent, "TextMessageEvent cannot be null!");

        doProcessing(textMessageEvent);
    }
}