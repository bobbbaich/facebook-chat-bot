package com.bobbbaich.fb.bot.cache.listener;

import com.bobbbaich.fb.bot.cache.api.StreamCacheService;
import com.bobbbaich.fb.bot.cache.event.AddStreamEvent;
import com.bobbbaich.fb.bot.cache.event.CloseStreamEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class StreamCacheListener {
    private final StreamCacheService service;

    @EventListener
    public void streamCloseEvent(CloseStreamEvent event) {
        log.debug("Was corrupted CloseStreamEvent");
        service.closeStream(event);
    }

    @EventListener
    public void streamAddEvent(AddStreamEvent event){
        log.debug("Was corrupted AddStreamEvent");
        service.addStream(event);
    }
}
