package com.sever0x.mailservice.mapper;

import com.sever0x.mailservice.messaging.EmailReceivedMessage;
import com.sever0x.mailservice.model.EmailMessage;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface EmailMessageMapper {

    EmailMessage messageToEntity(EmailReceivedMessage dto);

    EmailReceivedMessage entityToMessage(EmailMessage entity);
}
