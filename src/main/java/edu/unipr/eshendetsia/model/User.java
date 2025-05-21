package edu.unipr.eshendetsia.model;

import jakarta.persistence.*;
import lombok.Getter;

import lombok.Setter;

import java.util.Set;

/**
 * Klasa User perfaqeson nje perdorues ne sistemin e shendetesise.
 * Permban informacionin baze te perdoruesit dhe lidhjet me rolet.
 */
@Getter
@Setter
@Entity
@Table(name = "user_table")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Emri i perdoruesit
     */
    @Column(nullable = false)
    private String firstName;

    /**
     * Mbiemri i perdoruesit
     */
    @Column(nullable = false)
    private String lastName;

    /**
     * Adresa unike e emailit te perdoruesit
     */
    @Column(unique = true, nullable = false)
    private String email;

    /**
     * Fjalekalimi i enkriptuar i perdoruesit
     */
    @Column(nullable = false)
    private String password;

    /**
     * Numri i telefonit te perdoruesit
     */
    @Column(nullable = false)
    private int phoneNumber;

    /**
     * Vlera salt per enkriptimin e fjalekalimit
     */
    @Column(nullable = false)
    private String salt;

    /**
     * Lista e roleve qe ka perdoruesi
     */
    @ManyToMany
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles;

    /**
     * Historiku i perdoruesit ne sistem
     */
    @Column(nullable = false)
    private String history;

    /**
     * Lidhja me entitetin Doctor nese perdoruesi eshte doktor
     */
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Doctor doctor;

    /**
     * Lidhja me entitetin Admin nese perdoruesi eshte administrator
     */
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Admin admin;

    @Override
    public String toString() {
        return STR."User{id=\{id}, firstName='\{firstName}', lastName='\{lastName}', email='\{email}', password='\{password}', phoneNumber=\{phoneNumber}, salt='\{salt}'}";
    }
}