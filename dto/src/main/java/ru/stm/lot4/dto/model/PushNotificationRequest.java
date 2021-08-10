package ru.stm.lot4.dto.model;

import java.time.LocalDateTime;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PushNotificationRequest {

    private String title;
    private String body;
    private boolean active;
    private LocalDateTime date;
    private Set<String> phoneNumbers;
}
