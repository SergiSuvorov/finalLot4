package ru.stm.lot4.device.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.stm.lot4.db.model.Phone;
import ru.stm.lot4.db.repository.PhoneRepository;
import ru.stm.lot4.dto.mappers.PhoneMapper;
import ru.stm.lot4.dto.model.PhoneDTO;
@Service
@RequiredArgsConstructor
public class DeviceServiceImpl implements DeviceService{
    private final PhoneMapper phoneMapper;
    private final PhoneRepository phoneRepo;
    @Override
    public Phone createNewPhone(PhoneDTO phoneDTO) {
        Phone phone= phoneMapper.toEntity(phoneDTO);
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
    public Phone deactivatePhone(long id) {
        Phone phone = phoneRepo.findById(id)
                .orElse(null);
        if (phone==null) {
            return null;
        }
        phone.setActive(false);
        return this.phoneRepo.save(phone);
    }
}

