package com.bobbbaich.fb.bot.messenger.processor;

import com.github.messenger4j.webhook.Event;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class MessengerEventDelegate implements EventDelegate {
    private final LookupService lookupService;

    @Override
    public void onEvent(Event event) {
        EventProcessor eventProcessor = lookupService.getService(event);
        eventProcessor.doProcessing(event);
    }
}