package edu.unipr.eshendetsia.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


/**
 * Entiteti qe perfaqeson kontaktin emergjent te pacientit
 * Permban informacionet kryesore te personit qe duhet kontaktuar ne raste emergjente
 */
@Getter
@Setter
@Entity
@Table
public class EmergencyContact {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String phone;

    @Column
    private String relationship;
}
