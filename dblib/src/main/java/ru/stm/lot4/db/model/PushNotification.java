package ru.stm.lot4.db.model;

import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.experimental.Accessors;

@Entity(name = "push_notification")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class PushNotification {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NonNull
    private long id;
    @NotBlank
    private String title;
    @NotBlank
    private String body;
    private LocalDateTime date;
    @Column(name="is_active")
    private boolean active;
    @ManyToMany(targetEntity = Phone.class,fetch = FetchType.LAZY)
    @JoinColumn(name = "phones")
    private List<Phone> phones;
}
