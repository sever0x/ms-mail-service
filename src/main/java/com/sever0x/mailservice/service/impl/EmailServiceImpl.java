package com.sever0x.mailservice.service.impl;

import com.sever0x.mailservice.messaging.EmailReceivedMessage;
import com.sever0x.mailservice.mapper.EmailMessageMapper;
import com.sever0x.mailservice.model.EmailMessage;
import com.sever0x.mailservice.model.EmailStatus;
import com.sever0x.mailservice.repository.EmailMessageRepository;
import com.sever0x.mailservice.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final EmailMessageMapper emailMessageMapper;

    private final JavaMailSender mailSender;

    private final EmailMessageRepository emailMessageRepository;

    @Override
    public void sendEmail(EmailReceivedMessage receivedMessage) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject(receivedMessage.getSubject());
        message.setText(receivedMessage.getContent());
        message.setTo(receivedMessage.getRecipients().toArray(new String[0]));

        EmailMessage entityMessage = emailMessageMapper.messageToEntity(receivedMessage);

        try {
            mailSender.send(message);
            entityMessage.setStatus(EmailStatus.SENT);
        } catch (Exception e) {
            entityMessage.setStatus(EmailStatus.FAILED);
            entityMessage.setErrorMessage(e.getMessage());
        }

        emailMessageRepository.save(entityMessage);
    }
}
