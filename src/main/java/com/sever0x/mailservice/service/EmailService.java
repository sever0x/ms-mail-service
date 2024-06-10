package com.sever0x.mailservice.service;

import com.sever0x.mailservice.messaging.EmailReceivedMessage;
import com.sever0x.mailservice.model.EmailMessage;

public interface EmailService {

    void processReceivedMessage(EmailReceivedMessage message);

    void sendEmail(EmailMessage message);
}
