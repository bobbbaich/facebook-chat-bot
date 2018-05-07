package com.bobbbaich.fb.bot.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TweetMessage {
    private String recipientId;
    private long streamNumber;
    private String text;
}
