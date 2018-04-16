package com.bobbbaich.fb.bot.cache.api;

import com.bobbbaich.fb.bot.cache.event.AddStreamEvent;
import com.bobbbaich.fb.bot.cache.event.CloseStreamEvent;

public interface StreamCacheService {
    void closeStream(CloseStreamEvent event);

    void addStream(AddStreamEvent event);
}
