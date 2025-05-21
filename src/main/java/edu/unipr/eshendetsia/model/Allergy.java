package edu.unipr.eshendetsia.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Klasa qe perfaqeson informacionin e alergjeneve te pacientit
 * Permban informacionin per tipin e alergjise dhe reaksionet perkatese
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "allergy_table")
public class Allergy {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private Long userId;

    /**
     * Substanca apo medikamenti ndaj te cilit pacienti ka alergji
     */
    @Column(nullable = false)
    private String allergen;

    /**
     * Pershkrimi i reaksionit alergjik
     */
    @Column
    private String reaction;

    /**
     * Shenime shtese per alergjin
     */
    @Column
    private String notes;

}
