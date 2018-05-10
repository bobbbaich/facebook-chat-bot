package com.bobbbaich.twitter.cache.event;

import com.bobbbaich.twitter.cache.StreamInfo;
import com.bobbbaich.twitter.cache.api.Event;

public class AddEvent extends Event<StreamInfo> {
    public AddEvent(Object source, StreamInfo streamInfo) {
        super(source, streamInfo);
    }
}
