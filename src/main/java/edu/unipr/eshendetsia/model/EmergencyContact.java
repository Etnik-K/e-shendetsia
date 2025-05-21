package edu.unipr.eshendetsia.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * Entiteti qe perfaqeson kontaktin emergjent te pacientit
 * Permban informacionet kryesore te personit qe duhet kontaktuar ne raste emergjente
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
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
