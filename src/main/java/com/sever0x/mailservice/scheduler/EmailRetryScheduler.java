package com.sever0x.mailservice.scheduler;

import com.sever0x.mailservice.model.EmailMessage;
import com.sever0x.mailservice.model.EmailStatus;
import com.sever0x.mailservice.repository.EmailMessageRepository;
import com.sever0x.mailservice.service.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class EmailRetryScheduler {

    private final EmailService emailService;

    private final EmailMessageRepository emailMessageRepository;

    @Scheduled(fixedRateString = "${scheduler.fixed.rate}")
    public void retryFailedEmails() {
        List<EmailMessage> failedMessages = emailMessageRepository.findByStatus(EmailStatus.FAILED);

        for (EmailMessage message : failedMessages) {
            log.info(message.toString());
            emailService.sendEmail(message);
        }
    }
}
