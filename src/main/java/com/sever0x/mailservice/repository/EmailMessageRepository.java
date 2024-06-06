package com.sever0x.mailservice.repository;

import com.sever0x.mailservice.model.EmailMessage;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface EmailMessageRepository extends ElasticsearchRepository<EmailMessage, String> {
}
