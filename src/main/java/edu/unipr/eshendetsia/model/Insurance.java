package edu.unipr.eshendetsia.model;


import jakarta.persistence.*;
import lombok.*;

/**
 * Klasa Insurance perfaqeson informacionin e sigurimit te pacientit ne sistem.
 * Permban te dhenat e sigurimit duke perfshire:
 * - ID unike te sigurimit
 * - ID e perdoruesit
 * - Emrin e ofruesit te sigurimit
 * - Numrin e policise
 * - Detajet e mbulimit
 * - Statusin aktiv/joaktiv
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table
public class Insurance {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private String provider;

    @Column(nullable = false)
    private String policyNumber;

    @Column
    private String coverageDetails;

    @Column
    private boolean active = true;
}
