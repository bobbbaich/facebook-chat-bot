package com.bobbbaich.kafka.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
public class Message {
    private String recipientId;
    private String message;
}
