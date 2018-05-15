package com.bobbbaich.kafka.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TweetMessage {
    private String recipientId;
    private long streamNumber;
    private String text;
}
