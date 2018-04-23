package com.bobbbaich.fb.bot.cache.event;

import com.bobbbaich.fb.bot.cache.StreamInfo;
import com.bobbbaich.fb.bot.cache.api.Event;

public class AddEvent extends Event<StreamInfo> {
    public AddEvent(Object source, StreamInfo streamInfo) {
        super(source, streamInfo);
    }
}
