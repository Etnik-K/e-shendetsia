package edu.unipr.eshendetsia.model.entity;


import jakarta.persistence.*;
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
@Table(name = "feedback")
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
    @Column(nullable = false)
    private Long userId;

    /**
     * Identifikuesi i doktorit per te cilin eshte derguar reagimi
     */
    @Column(nullable = false)
    private Long doctorId;

    /**
     * Mesazhi i reagimit nga pacienti per doktorin
     */
    @Column(nullable = false)
    private String message;

    /**
     * Vleresimi numerik nga 1-5 per doktorin
     */
    @Column
    private Integer rating;

    /**
     * Data dhe ora kur eshte derguar reagimi
     */
    @Column
    private LocalDateTime submittedAt = LocalDateTime.now();

}
