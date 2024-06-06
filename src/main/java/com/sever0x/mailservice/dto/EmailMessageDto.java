package com.sever0x.mailservice.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class EmailMessageDto {
    private String subject;

    private String content;

    private List<String> recipients;

    private String status;

    private String errorMessage;

    private int attempt;

    private LocalDateTime lastAttemptTime;
}
