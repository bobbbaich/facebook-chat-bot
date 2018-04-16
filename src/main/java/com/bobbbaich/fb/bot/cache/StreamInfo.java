package com.bobbbaich.fb.bot.cache;

import lombok.Data;
import org.springframework.social.twitter.api.Stream;

@Data
public class StreamInfo {
    private String topic;
    private String keyWord;
    private Stream stream;
}
