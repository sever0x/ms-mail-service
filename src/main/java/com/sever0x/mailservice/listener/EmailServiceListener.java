package com.sever0x.mailservice.listener;

import com.sever0x.mailservice.messaging.EmailReceivedMessage;
import com.sever0x.mailservice.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmailServiceListener {

    private final EmailService emailService;

    @KafkaListener(topics = "${kafka.topic.email.service}")
    public void sendEmail(EmailReceivedMessage message) {
        emailService.sendEmail(message);
    }
}
