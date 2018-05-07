package com.bobbbaich.fb.bot.cache.event;

import com.bobbbaich.fb.bot.cache.StreamInfo;
import com.bobbbaich.fb.bot.cache.api.Event;
import lombok.Data;
import org.springframework.context.ApplicationEvent;

public class CloseEvent extends Event<StreamInfo> {
    public CloseEvent(Object source, StreamInfo streamInfo) {
        super(source, streamInfo);
    }
}
