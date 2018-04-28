package com.bobbbaich.fb.bot.messenger.processor;

import com.bobbbaich.fb.bot.messenger.config.MessengerProperty;
import com.github.messenger4j.webhook.Event;
import com.github.messenger4j.webhook.event.PostbackEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import static com.bobbbaich.fb.bot.messenger.config.Postback.*;

@Slf4j
@RequiredArgsConstructor
@Component
public class PostbackEventProcessor implements EventProcessor<PostbackEvent> {
    private final MessengerProperty props;

    @Override
    public void doProcessing(PostbackEvent event) {
        log.debug("PostbackEvent has been processing...");
        String payload = event.payload().orElseThrow(() -> new IllegalArgumentException("Unsupported postback, payload is null!"));

        switch (payload) {
            case GET_STARTED_PAYLOAD:
                break;
            case START_ANALYSIS_PAYLOAD:
                break;
            case HELP_PAYLOAD:
                break;
            default:
                break;
        }
    }

    @Override
    public void doProcessing(Event event) {
        PostbackEvent postbackEvent = event.asPostbackEvent();
        Assert.notNull(postbackEvent, "PostbackEvent cannot be null!");

        doProcessing(postbackEvent);
    }
}