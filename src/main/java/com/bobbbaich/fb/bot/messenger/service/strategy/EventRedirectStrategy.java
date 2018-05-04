package com.bobbbaich.fb.bot.messenger.service.strategy;

import com.bobbbaich.fb.bot.cache.api.Event;
import com.bobbbaich.fb.bot.messenger.service.annotaion.EventQualifier;
import com.bobbbaich.fb.bot.model.Message;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@EventQualifier
@Component
public class EventRedirectStrategy implements MessageRedirectStrategy<Message> {
    private final ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void redirect(Message message) {
        applicationEventPublisher.publishEvent(AnalyseEvent.supply(this, message));
        log.debug("Publish message for running analyse");
    }

    private static class AnalyseEvent extends Event<Message> {

        private AnalyseEvent(Object source, Message eventObj) {
            super(source, eventObj);
        }

        private static Event<Message> supply(Object source, Message obj) {
            return new AnalyseEvent(source, obj);
        }
    }
}
