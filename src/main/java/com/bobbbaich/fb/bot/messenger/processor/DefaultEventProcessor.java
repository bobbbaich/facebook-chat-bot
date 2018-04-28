package com.bobbbaich.fb.bot.messenger.processor;

import com.github.messenger4j.webhook.Event;
import com.github.messenger4j.webhook.event.BaseEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class DefaultEventProcessor implements EventProcessor<BaseEvent> {
    @Override
    public void doProcessing(BaseEvent event) {
        log.debug("There is no implementation for event: {}", event);
    }

    @Override
    public void doProcessing(Event event) {
        log.debug("There is no implementation for event: {}", event);
    }
}