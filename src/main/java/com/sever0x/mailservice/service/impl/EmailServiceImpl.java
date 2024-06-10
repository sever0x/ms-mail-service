package com.sever0x.mailservice.service.impl;

import com.sever0x.mailservice.mapper.EmailMessageMapper;
import com.sever0x.mailservice.messaging.EmailReceivedMessage;
import com.sever0x.mailservice.model.EmailMessage;
import com.sever0x.mailservice.model.EmailStatus;
import com.sever0x.mailservice.repository.EmailMessageRepository;
import com.sever0x.mailservice.service.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final EmailMessageMapper emailMessageMapper;

    private final JavaMailSender mailSender;

    private final EmailMessageRepository emailMessageRepository;

    @Override
    public void processReceivedMessage(EmailReceivedMessage message) {
        EmailMessage entityMessage = emailMessageMapper.messageToEntity(message);
        sendEmail(entityMessage);
    }

    @Override
    public void sendEmail(EmailMessage message) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setSubject(message.getSubject());
        mailMessage.setText(message.getContent());
        mailMessage.setTo(message.getRecipients().toArray(new String[0]));

        try {
            mailSender.send(mailMessage);
            message.setStatus(EmailStatus.SENT);
        } catch (MailException e) {
            log.error(e.getMessage());
            message.setStatus(EmailStatus.FAILED);
            message.setErrorMessage(e.getMessage());
        }

        message.setAttempt(message.getAttempt() + 1);
        message.setLastAttemptTime(Instant.now());

        log.info(message.toString());

        emailMessageRepository.save(message);
    }
}
