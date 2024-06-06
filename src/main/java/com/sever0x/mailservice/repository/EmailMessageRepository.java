package com.sever0x.emailservice.repository;

import com.sever0x.emailservice.model.EmailMessage;
import org.springframework.data.repository.CrudRepository;

public interface EmailMessageRepository extends CrudRepository<EmailMessage, String> {
}
