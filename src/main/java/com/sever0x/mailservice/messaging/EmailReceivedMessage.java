package com.sever0x.mailservice.messaging;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@Getter
@Builder
@Jacksonized
public class EmailReceivedMessage {
    private String subject;

    private String content;

    private List<String> recipients;
}
