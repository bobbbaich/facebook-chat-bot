package com.bobbbaich.messenger.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Message {
    private String recipientId;
    private String message;
}
