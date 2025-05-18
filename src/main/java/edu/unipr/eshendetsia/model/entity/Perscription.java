package edu.unipr.eshendetsia.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

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
@Getter
@Setter
@Entity
@Table(name="perscriptions")
public class Perscription {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private Long doctorId;

    @Column(nullable = false)
    private String medication;

    @Column(nullable = false)
    private String dosage;

    @Column
    private String instructions;

    @Column
    private LocalDateTime issuedAt = LocalDateTime.now();

}
