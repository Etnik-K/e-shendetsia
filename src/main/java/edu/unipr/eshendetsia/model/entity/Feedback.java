package edu.unipr.eshendetsia.model.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDateTime;


/**
 * Entiteti qe perdoret per te ruajtur te dhenat e reagimeve
 * te pacienteve per doktoret e tyre. Permban informacionin
 * lidhur me perdoruesin, doktorin, mesazhin, vleresimin dhe
 * kohen e dergimit.
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "feedback_table")
public class Feedback {

    /**
     * Identifikuesi unik i reagimit
     */
    @Id
    @GeneratedValue
    private Long id;

    /**
     * Identifikuesi i perdoruesit qe ka derguar reagimin
     */
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    /**
     * Identifikuesi i doktorit per te cilin eshte derguar reagimi
     */
    @ManyToOne
    @JoinColumn(name = "doctor_id", nullable = false)
    private Doctor doctor;

    /**
     * Mesazhi i reagimit nga pacienti per doktorin
     */
    @NotBlank
    @Column(nullable = false, length = 510)
    private String message;

    /**
     * Vleresimi numerik nga 1-5 per doktorin
     */
    @Min(1)
    @Max(5)
    private Integer rating;

    /**
     * Data dhe ora kur eshte derguar reagimi
     */
    @Column(nullable = false)
    private LocalDateTime submittedAt = LocalDateTime.now();

    @PrePersist
    public void prePersist() {
        if (submittedAt == null)
            submittedAt = LocalDateTime.now();
    }
}
