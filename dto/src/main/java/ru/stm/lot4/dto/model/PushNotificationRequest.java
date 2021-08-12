package ru.stm.lot4.dto.model;

import java.time.LocalDateTime;
import java.util.Set;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PushNotificationRequest {

    @NotBlank
    private String title;
    @NotBlank
    private String body;
    private boolean active;
    private LocalDateTime date;
    @NotNull
    private Set<String> phoneNumbers;
}
