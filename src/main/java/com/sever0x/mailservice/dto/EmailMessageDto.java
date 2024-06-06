package com.sever0x.emailservice.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class EmailMessageDto {
    private String subject;
    private String content;
    private List<String> recipients;
    private String status;
    private String errorMessage;
    private int attempt;
    private LocalDateTime lastAttemptTim;
}
