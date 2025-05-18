package edu.unipr.eshendetsia.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

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
@Setter
@Getter
@Entity
@Table(name = "referrals")
public class Referral {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private Long patientId;

    @Column(nullable = false)
    private Long fromDoctorId;

    @Column(nullable = false)
    private Long toDoctorId;

    @Column(nullable = false)
    private String reason;

    @Column
    private LocalDateTime referralDate = LocalDateTime.now();
}
