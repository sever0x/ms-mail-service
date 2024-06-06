package com.sever0x.emailservice.service;

import com.sever0x.emailservice.dto.EmailMessageDto;

public interface EmailService {

    void sendEmail(EmailMessageDto dto);
}
