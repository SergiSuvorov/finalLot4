package ru.stm.lot4.dto.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import ru.stm.lot4.db.model.Phone;
import ru.stm.lot4.dto.model.PhoneDTO;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PhoneMapper {

    PhoneDTO toDTO(Phone phone);
    Phone toEntity(PhoneDTO phoneDTO);
}
