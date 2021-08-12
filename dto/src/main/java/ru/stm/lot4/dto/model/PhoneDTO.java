package ru.stm.lot4.dto.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import ru.stm.lot4.db.model.Application;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PhoneDTO {

    @Pattern(regexp = "^((\\+7)+([0-9]){10})$", message = "Неверный формат номера телефона")
    @NotBlank
    private String number;
    private boolean active;
   // @NonNull
    private Application application;
}
