package ru.stm.lot4.db.model;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity(name = "push_notification")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PushNotification {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NonNull
    private long id;
    private String title;
    private String body;
    private LocalDateTime date;
    @Column(name="is_active")
    private boolean active;
}
