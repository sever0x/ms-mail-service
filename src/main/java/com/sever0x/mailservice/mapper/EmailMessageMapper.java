package com.sever0x.emailservice.mapper;

import com.sever0x.emailservice.dto.EmailMessageDto;
import com.sever0x.emailservice.model.EmailMessage;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface EmailMessageMapper {

    EmailMessage dtoToEntity(EmailMessageDto dto);
}
