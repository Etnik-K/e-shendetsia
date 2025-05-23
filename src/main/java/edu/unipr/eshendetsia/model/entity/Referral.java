package edu.unipr.eshendetsia.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

/**
 * Klasa qe perfaqeson nje referim nga nje doktor tek nje tjeter
 * per nje pacient te caktuar.
 * <p>
 * Atributet:
 * - id: Identifikuesi unik i referimit
 * - patientId: ID e pacientit qe referohet
 * - fromDoctorId: ID e doktorit qe ben referimin
 * - toDoctorId: ID e doktorit tek i cili behet referimi
 * - reason: Arsyeja e referimit
 * - referralDate: Data dhe ora kur eshte bere referimi
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "referrals")
public class Referral {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "patient_id", nullable = false)
    private User patient;

    @ManyToOne(optional = false)
    @JoinColumn(name = "from_doctor_id", nullable = false)
    private Doctor fromDoctor;

    @ManyToOne(optional = false)
    @JoinColumn(name = "to_doctor_id", nullable = false)
    private Doctor toDoctor;

    @Column
    private String reason;

    @Column(nullable = false)
    private LocalDateTime referralDate;

    @PrePersist
    public void prePersist() {
        if (referralDate == null)
            referralDate = LocalDateTime.now();
    }
}
