package com.bobbbaich.messenger.processor;


import com.bobbbaich.messenger.processor.api.EventProcessor;
import com.bobbbaich.messenger.service.api.BotService;
import com.github.messenger4j.exception.MessengerApiException;
import com.github.messenger4j.exception.MessengerIOException;
import com.github.messenger4j.webhook.Event;
import com.github.messenger4j.webhook.event.PostbackEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import static com.bobbbaich.messenger.config.PostbackPayload.*;

@Slf4j
@RequiredArgsConstructor
@Component
public class PostbackEventProcessor implements EventProcessor<PostbackEvent> {
    private final BotService botService;

    @Override
    public void doProcessing(PostbackEvent event) throws MessengerApiException, MessengerIOException {
        log.debug("PostbackEvent has been processing...");
        log.debug("PostbackEvent = {}", event);
        log.debug("PostbackEvent payload = {}", event.payload());
        String payload = event.payload().orElseThrow(() -> new IllegalArgumentException("Unsupported postback, payload is null!"));

        final String recipientId = event.senderId();
        switch (payload) {
            case GET_STARTED_PAYLOAD:
                botService.createUser(recipientId);
                botService.getHelp(recipientId);
                break;
            case START_ANALYSIS_PAYLOAD:
                botService.startAnalysis(recipientId);
                break;
            case HELP_PAYLOAD:
                botService.getHelp(recipientId);
                break;
            default:
                break;
        }
    }

    @Override
    public void doProcessing(Event event) throws MessengerApiException, MessengerIOException {
        PostbackEvent postbackEvent = event.asPostbackEvent();
        Assert.notNull(postbackEvent, "PostbackEvent cannot be null!");

        doProcessing(postbackEvent);
    }
}