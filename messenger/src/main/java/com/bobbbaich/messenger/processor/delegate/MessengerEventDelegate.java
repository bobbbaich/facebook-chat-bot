package com.bobbbaich.messenger.processor.delegate;

import com.bobbbaich.messenger.exception.MessengerException;
import com.bobbbaich.messenger.processor.api.EventProcessor;
import com.github.messenger4j.exception.MessengerApiException;
import com.github.messenger4j.exception.MessengerIOException;
import com.github.messenger4j.webhook.Event;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class MessengerEventDelegate implements EventDelegate {
    private final ProcessorLookup processorLookup;

    @Override
    public void onEvent(Event event) {
        try {
            EventProcessor eventProcessor = processorLookup.getProcessor(event);
            eventProcessor.doProcessing(event);
        } catch (MessengerApiException | MessengerIOException e) {
            log.error("Exception occurred when event has been processing.", e);
            throw new MessengerException("Exception occurred when event has been processing.", e);
        }
    }
}