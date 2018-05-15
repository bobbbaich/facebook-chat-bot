package com.bobbbaich.twitter.cache.listener;

import com.bobbbaich.twitter.cache.StreamInfo;
import com.bobbbaich.twitter.cache.api.CacheService;
import com.bobbbaich.twitter.cache.api.Event;
import com.bobbbaich.twitter.cache.event.AddEvent;
import com.bobbbaich.twitter.cache.event.CloseEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class CacheListener {
    private final CacheService<Event<StreamInfo>> service;

    @EventListener
    public void close(CloseEvent event) {
        log.debug("Was corrupted CloseStreamEvent");
        service.remove(event);
    }

    @EventListener
    public void add(AddEvent event) {
        log.debug("Was corrupted AddStreamEvent");
        service.add(event);
    }
}
