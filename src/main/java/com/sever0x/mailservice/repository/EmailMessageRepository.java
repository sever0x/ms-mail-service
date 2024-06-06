package com.sever0x.mailservice.repository;

import com.sever0x.mailservice.model.EmailMessage;
import org.springframework.data.repository.CrudRepository;

public interface EmailMessageRepository extends CrudRepository<EmailMessage, String> {
}
