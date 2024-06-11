package com.sever0x.mailservice.service;

import com.sever0x.mailservice.MsMailServiceApplication;
import com.sever0x.mailservice.config.TestElasticsearchConfiguration;
import com.sever0x.mailservice.model.EmailMessage;
import com.sever0x.mailservice.model.EmailStatus;
import com.sever0x.mailservice.repository.EmailMessageRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;

@SpringBootTest
@ActiveProfiles("test")
@ContextConfiguration(classes = {MsMailServiceApplication.class, TestElasticsearchConfiguration.class})
class EmailServiceIntegrationTests {

    @MockBean
    private JavaMailSender mailSender;

    @Autowired
    private ElasticsearchOperations elasticsearchOperations;

    @Autowired
    private EmailService emailService;

    @Autowired
    private EmailMessageRepository emailMessageRepository;

    @BeforeEach
    public void beforeEach() {
        elasticsearchOperations.indexOps(EmailMessage.class).createMapping();
    }

    @AfterEach
    public void afterEach() {
        elasticsearchOperations.indexOps(EmailMessage.class).delete();
    }

    @Test
    void shouldSendEmailSuccess() throws Exception {
        EmailMessage emailMessage = createEmailMessage();
        emailService.sendEmail(emailMessage);

        List<EmailMessage> sentMessages = emailMessageRepository.findByStatus(EmailStatus.SENT);
        assertThat(sentMessages).hasSize(1);
        assertThat(sentMessages.get(0).getStatus()).isEqualTo(EmailStatus.SENT);
    }

    @Test
    void shouldSendEmailFailure() throws Exception {
        doThrow(new MailException("Test Exception") {}).when(mailSender).send(any(SimpleMailMessage.class));

        EmailMessage emailMessage = createEmailMessage();
        emailService.sendEmail(emailMessage);

        List<EmailMessage> failedMessages = emailMessageRepository.findByStatus(EmailStatus.FAILED);
        assertThat(failedMessages).hasSize(1);
        assertThat(failedMessages.get(0).getStatus()).isEqualTo(EmailStatus.FAILED);
        assertThat(failedMessages.get(0).getErrorMessage()).isEqualTo("Test Exception");
    }

    private EmailMessage createEmailMessage() {
        EmailMessage emailMessage = new EmailMessage();
        emailMessage.setSubject("Test Subject");
        emailMessage.setContent("Test Content");
        emailMessage.setRecipients(List.of("test@example.com"));
        emailMessage.setStatus(EmailStatus.FAILED);
        emailMessage.setAttempt(0);
        return emailMessage;
    }
}
