package edu.unipr.eshendetsia.model.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.Set;

/**
 * Pershkruan entitetin e doktorit ne sistemin e menaxhimit shendetesor.
 * <p>
 * Perfshin informacionin personal te doktorit, profesionin dhe licensen.
 * Lidhet me entitetin e perdoruesit dhe klinikat ku punon.
 * <p>
 * Atributet:
 * - id: Identifikuesi unik i doktorit
 * - user: Lidhja me te dhenat e perdoruesit
 * - employedBy: Set i klinikave ku punon doktori
 * - profesioni: Specializimi i doktorit
 * - licensa: Numri i licenses profesionale
 */
@Getter
@Entity
@Table(name = "doctor_table")
public class Doctor {

    @Id
    private Long id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "id")
    private User user;

    @ManyToMany
    @JoinTable(
            name = "employed_by",
            joinColumns = @JoinColumn(name = "doctor_id"),
            inverseJoinColumns = @JoinColumn(name = "clinic_id")
    )
    private Set<Clinic> employedBy;

    @Column(nullable = false)
    private String profesioni;

    @Column(nullable = false)
    private String licensa;

}