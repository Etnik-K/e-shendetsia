package millaku.altin.eshendetsia.model.entity;

import millaku.altin.eshendetsia.model.enums.AppointmentStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDateTime;

/**
 * Klasa qe perfaqeson takimet e pacienteve me doktoret ne sistem.
 * Permban informacionet e nevojshme per menaxhimin e takimeve,
 * duke perfshire ID e pacientit, doktorit, kohen e takimit,
 * arsyen dhe statusin e takimit.
 */
@AllArgsConstructor
@NoArgsConstructor
@Data // getter-setter-tostring-hashcode-equals-requiredArgsConstructor
@Entity
@Table(name = "appointment_table")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "doctor_id", nullable = false)
    private Doctor doctor;

    @Future(message = "Appointment time must be in the future")
    @Column(nullable = false)
    private LocalDateTime appointmentTime;

    @NotBlank
    @Lob
    private String reason;

    @Enumerated(EnumType.STRING)
    private AppointmentStatus status = AppointmentStatus.SCHEDULED;

    @PrePersist
    public void prePersist() {
        if (status == null)
            status = AppointmentStatus.SCHEDULED;
    }
}
