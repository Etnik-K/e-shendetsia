package millaku.altin.eshendetsia.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

/**
 * Klasa Perscription perfaqeson receten mjekesore ne sistem.
 * <p>
 * Fushat:
 * - id: Identifikuesi unik i recetes
 * - userId: Identifikuesi i pacientit
 * - doctorId: Identifikuesi i mjekut
 * - medication: Emri i medikamentit
 * - dosage: Doza e medikamentit
 * - instructions: Udhezime shtese
 * - issuedAt: Data dhe ora e leshimit
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="perscription_table")
public class Perscription {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(optional = false)
    @JoinColumn(name = "doctor_id", nullable = false)
    private Doctor doctor;

    @Column(nullable = false)
    private String medication;

    @Column(nullable = false)
    private String dosage;

    @Column
    private String frequency;

    @Column
    private String duration;

    @Column(nullable = false)
    private LocalDateTime issuedAt;

    @PrePersist
    public void prePersist() {
        if (issuedAt == null)
            issuedAt = LocalDateTime.now();
    }

}
