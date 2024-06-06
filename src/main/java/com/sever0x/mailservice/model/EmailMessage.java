package com.sever0x.emailservice.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Document(indexName = "emailMessages")
public class EmailMessage {

    @Id
    private String id;

    private String subject;

    private String content;

    private List<String> recipients;

    private EmailStatus status;

    private String errorMessage;

    private int attempt;

    private LocalDateTime lastAttemptTime;
}
