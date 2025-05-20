package edu.unipr.eshendetsia.model.entity;

import jakarta.persistence.*;
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
@Data // getter-setter-tostring-hashcode-equals
@Entity
@Table(name = "appointment_table")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private Long doctorId;

    @Column(nullable = false)
    private LocalDateTime appointmentTime;

    @Column(nullable = false)
    private String reason;

    @Column
    private String Status;
}
