package com.sever0x.mailservice.service;

import com.sever0x.mailservice.messaging.EmailReceivedMessage;

public interface EmailService {

    void sendEmail(EmailReceivedMessage dto);
}
