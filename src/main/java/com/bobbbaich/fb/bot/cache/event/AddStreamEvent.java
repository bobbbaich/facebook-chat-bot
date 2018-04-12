package com.bobbbaich.fb.bot.cache.event;

import com.bobbbaich.fb.bot.cache.StreamInfo;
import lombok.Data;
import org.springframework.context.ApplicationEvent;

@Data
public class AddStreamEvent extends ApplicationEvent {
    private StreamInfo streamInfo;

    public AddStreamEvent(Object source, StreamInfo streamInfo) {
        super(source);
        this.streamInfo = streamInfo;
    }
}
