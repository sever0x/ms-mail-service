package com.sever0x.mailservice.service;

import com.sever0x.mailservice.dto.EmailMessageDto;

public interface EmailService {

    void sendEmail(EmailMessageDto dto);
}
