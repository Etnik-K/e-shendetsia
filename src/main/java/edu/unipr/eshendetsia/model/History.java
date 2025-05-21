package edu.unipr.eshendetsia.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

/**
 * Klasa History perfaqson te dhenat historike te nje vizite mjekesore
 * dhe permban informacione si:
 * - ID e perdoruesit
 * - ID e mjekut
 * - Pershkrimin
 * - Diagnozen
 * - Trajtimin
 * - Daten e vizites
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "history_table")
public class History {

    @Id
    @GeneratedValue
    private Long id;

    //KlinikaID
    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private Long doctorId;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String diagnosis;

    @Column(nullable = false)
    private String treatment;

    @Column(nullable = false)
    private LocalDateTime date;
}
