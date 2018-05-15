package com.bobbbaich.twitter.cache.event;

import com.bobbbaich.twitter.cache.StreamInfo;
import com.bobbbaich.twitter.cache.api.Event;

public class CloseEvent extends Event<StreamInfo> {
    public CloseEvent(Object source, StreamInfo streamInfo) {
        super(source, streamInfo);
    }
}
