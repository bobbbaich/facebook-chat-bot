package com.bobbbaich.fb.bot.messenger.processor;

import com.github.messenger4j.webhook.Event;
import com.github.messenger4j.webhook.event.BaseEvent;
import com.github.messenger4j.webhook.event.PostbackEvent;
import com.github.messenger4j.webhook.event.TextMessageEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Slf4j
@RequiredArgsConstructor
@Service
public class ProcessorLookupService implements ProcessorLookup {
    private final EventProcessor<BaseEvent> defaultEventProcessor;
    private final EventProcessor<PostbackEvent> postbackEventProcessor;
    private final EventProcessor<TextMessageEvent> textMessageEventProcessor;

    @Override
    public EventProcessor getProcessor(Event event) {
        Assert.notNull(event, "Event cannot be null!");

        if (event.isPostbackEvent()) return postbackEventProcessor;
        else if (event.isTextMessageEvent()) return textMessageEventProcessor;
        else return defaultEventProcessor;
    }
}