package com.sever0x.mailservice.mapper;

import com.sever0x.mailservice.dto.EmailMessageDto;
import com.sever0x.mailservice.model.EmailMessage;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface EmailMessageMapper {

    EmailMessage dtoToEntity(EmailMessageDto dto);
}
