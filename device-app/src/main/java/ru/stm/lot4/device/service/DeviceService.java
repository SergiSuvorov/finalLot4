package ru.stm.lot4.device.service;

import org.springframework.stereotype.Service;
import ru.stm.lot4.db.model.Phone;
import ru.stm.lot4.dto.model.PhoneDTO;

@Service
public interface DeviceService {

    Phone createNewPhone(PhoneDTO phoneDTO);

    Phone getPhoneById(long id);

    void deletePhone(Phone toDeletePhone);

    Phone deactivatePhone(long id);
}
