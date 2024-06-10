package com.sever0x.mailservice.repository;

import com.sever0x.mailservice.model.EmailMessage;
import com.sever0x.mailservice.model.EmailStatus;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface EmailMessageRepository extends ElasticsearchRepository<EmailMessage, String> {

    List<EmailMessage> findByStatus(EmailStatus status);
}
