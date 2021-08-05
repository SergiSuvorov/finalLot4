package ru.stm.lot4.db.model;

import javax.persistence.Column;
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

@Entity(name = "phone")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Phone {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NonNull
    private long id;
    private String number;
    @Column(name = "is_active")
    private boolean active;
    @ManyToOne(targetEntity = Application.class)
    @JoinColumn(name = "mobile_application_id")
    private Application application;
}
