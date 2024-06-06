package com.sever0x.mailservice.service.impl;

import com.sever0x.mailservice.dto.EmailMessageDto;
import com.sever0x.mailservice.mapper.EmailMessageMapper;
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
    public void sendEmail(EmailMessageDto dto) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject(dto.getSubject());
        message.setText(dto.getContent());
        message.setTo(dto.getRecipients().toArray(new String[0]));

        try {
            mailSender.send(message);
            dto.setStatus(EmailStatus.SENT.name());
        } catch (Exception e) {
            dto.setStatus(EmailStatus.FAILED.name());
            dto.setErrorMessage(e.getMessage());
        }

        emailMessageRepository.save(emailMessageMapper.dtoToEntity(dto));
    }
}
