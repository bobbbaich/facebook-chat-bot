package com.bobbbaich.twitter.cache;

import lombok.Data;
import org.springframework.social.UserIdSource;
import org.springframework.social.twitter.api.Stream;

import java.util.UUID;

@Data
public class StreamInfo {
    private String recipientId;
    private long streamNumber;
    private Stream stream;
}
