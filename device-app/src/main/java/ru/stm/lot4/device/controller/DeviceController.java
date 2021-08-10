package ru.stm.lot4.device.controller;

import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.stm.lot4.db.model.Phone;
import ru.stm.lot4.device.service.DeviceService;
import ru.stm.lot4.dto.model.PhoneDTO;

@RestController
@RequestMapping("/device")
@RequiredArgsConstructor
@Validated
public class DeviceController {

    private final DeviceService deviceService;

    @PostMapping
    public ResponseEntity<Phone> createNewPhone(@Valid @RequestBody PhoneDTO phoneDTO) {
        Phone newPhone = deviceService.createNewPhone(phoneDTO);
        if (newPhone == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(newPhone, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Phone> deleteCurrentPhone(@PathVariable("id") long id) {
        Phone toDeletePhone = deviceService.getPhoneById(id);
        if (toDeletePhone == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        deviceService.deletePhone(toDeletePhone);
        return new ResponseEntity<>(toDeletePhone, HttpStatus.OK);
    }

    @PutMapping("{id}/deactivate")
    public ResponseEntity<Phone> deactivatePhone(@PathVariable("id") long id) {
        Phone currPhone = deviceService.deactivatePhone(id);
        if (currPhone == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(currPhone, HttpStatus.OK);
    }
}
