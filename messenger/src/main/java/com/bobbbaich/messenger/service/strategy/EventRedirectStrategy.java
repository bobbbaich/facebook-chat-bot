package com.bobbbaich.messenger.service.strategy;

import com.bobbbaich.messenger.model.Event;
import com.bobbbaich.messenger.model.Message;
import com.bobbbaich.messenger.service.annotaion.EventQualifier;
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
