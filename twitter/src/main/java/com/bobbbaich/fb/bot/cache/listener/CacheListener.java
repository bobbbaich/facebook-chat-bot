package com.bobbbaich.fb.bot.cache.listener;

import com.bobbbaich.fb.bot.cache.StreamInfo;
import com.bobbbaich.fb.bot.cache.api.CacheService;
import com.bobbbaich.fb.bot.cache.api.Event;
import com.bobbbaich.fb.bot.cache.event.AddEvent;
import com.bobbbaich.fb.bot.cache.event.CloseEvent;
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
