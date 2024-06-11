package com.sever0x.mailservice.listener;

import com.sever0x.mailservice.messaging.EmailReceivedMessage;
import com.sever0x.mailservice.service.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.kafka.core.KafkaOperations;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.after;
import static org.mockito.Mockito.verify;

@Slf4j
@SpringBootTest
@DirtiesContext
@ActiveProfiles("test")
@EmbeddedKafka(partitions = 1, brokerProperties = { "listeners=PLAINTEXT://localhost:9092", "port=9092" })
class EmailServiceListenerTests {

    @Value("${kafka.topic.email.service}")
    private String emailServiceTopic;

    @Autowired
    KafkaOperations<String, EmailReceivedMessage> kafkaOperations;

    @SpyBean
    private EmailService emailService;

    @Test
    void testEmailReceived() {
        EmailReceivedMessage message = EmailReceivedMessage.builder()
                .content("Test content")
                .subject("Test subject")
                .recipients(List.of("example@mail.com"))
                .build();

        kafkaOperations.send(emailServiceTopic, message);
        verify(emailService, after(10000)).processReceivedMessage(any());
    }
}
