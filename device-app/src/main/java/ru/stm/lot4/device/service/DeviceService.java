package ru.stm.lot4.device.service;

import ru.stm.lot4.db.model.Phone;
import ru.stm.lot4.dto.model.PhoneDTO;

public interface DeviceService {

    Phone createNewPhone(PhoneDTO phoneDTO);

    Phone getPhoneById(long id);

    void deletePhone(Phone toDeletePhone);

    Phone deactivatePhone(long id);
}
