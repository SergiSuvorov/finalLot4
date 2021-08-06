package ru.stm.lot4.dto.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import ru.stm.lot4.db.model.PushNotification;
import ru.stm.lot4.dto.model.PushNotificationDTO;
import ru.stm.lot4.dto.model.PushNotificationRequest;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PushNotificationMapper {

    PushNotification toEntity(PushNotificationDTO dto);
    PushNotificationDTO toDTO(PushNotification notification);
    @Mapping(source = "phoneNumbers", target = "phones", ignore = true)
    PushNotification fromRequest(PushNotificationRequest request);
}
