package ru.stm.lot4.device.service.impl;

import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.stm.lot4.db.model.Phone;
import ru.stm.lot4.db.repository.PhoneRepository;
import ru.stm.lot4.device.service.DeviceService;
import ru.stm.lot4.dto.mappers.PhoneMapper;
import ru.stm.lot4.dto.model.PhoneDTO;

@Service
@RequiredArgsConstructor
public class DeviceServiceImpl implements DeviceService {

    private final PhoneMapper phoneMapper;
    private final PhoneRepository phoneRepo;

    @Override
    public Phone createNewPhone(PhoneDTO phoneDTO) {
        Phone phone = phoneMapper.toEntity(phoneDTO);
        return phoneRepo.save(phone);
    }

    @Override
    public Phone getPhoneById(long id) {
        return phoneRepo.findById(id)
                .orElse(null);
    }

    @Override
    public void deletePhone(Phone toDeletePhone) {
        phoneRepo.delete(toDeletePhone);
    }

    @Override
    @Transactional
    public Phone deactivatePhone(long id) {

        return phoneRepo.findById(id)
                .map(phone1 -> phone1.setActive(false))
                .map(phoneRepo::save)
                .orElse(null);
    }
}

