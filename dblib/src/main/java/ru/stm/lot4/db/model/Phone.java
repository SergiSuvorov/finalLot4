package ru.stm.lot4.db.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.experimental.Accessors;

@Entity(name = "phone")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Phone {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NonNull
    private long id;
    @NotBlank
    @Pattern(regexp = "^((\\+7)+([0-9]){10})$")
    private String number;
    @Column(name = "is_active")
    private boolean active;
    @ManyToOne(targetEntity = Application.class)
    @JoinColumn(name = "mobile_application_id")
    private Application application;
}
