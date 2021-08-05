package ru.stm.lot4.db.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PushNotificationPhone {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NonNull
    private long id;
    @ManyToOne(targetEntity = Phone.class)
    @JoinColumn(name = "phone_id")
    private Phone phone;
    @ManyToOne(targetEntity = PushNotification.class)
    @JoinColumn(name = "push_notification_id")
    private PushNotification pushNotification;
}
