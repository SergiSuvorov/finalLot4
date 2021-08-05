package ru.stm.lot4.dto.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.stm.lot4.db.model.Application;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PhoneDTO {
    private String number;
    private boolean active;
    private Application application;
}
