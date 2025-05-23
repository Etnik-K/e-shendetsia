package edu.unipr.eshendetsia.model.entity;

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

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "doctor_id", nullable = false)
    private Doctor doctor;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String diagnosis;

    @Column(nullable = false)
    private String treatment;

    @Column(nullable = false)
    private LocalDateTime date;

    @PrePersist
    public void prePersist() {
        if (date == null)
            date = LocalDateTime.now();
    }
}
