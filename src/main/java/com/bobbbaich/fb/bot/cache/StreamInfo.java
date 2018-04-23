package com.bobbbaich.fb.bot.cache;

import lombok.Data;
import org.springframework.social.UserIdSource;
import org.springframework.social.twitter.api.Stream;

import java.util.UUID;

@Data
public class StreamInfo {
    private String topic;
    private String keyWord;
    private Stream stream;
}
