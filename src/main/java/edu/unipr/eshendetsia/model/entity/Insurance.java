package edu.unipr.eshendetsia.model.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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
@Table(
    name = "insurance_table",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = {"user_id", "policyNumber"})
    }
)
public class Insurance {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @NotBlank
    @Column(nullable = false)
    private String provider;

    @NotBlank
    @Column(nullable = false)
    private String policyNumber;

    @Lob
    private String coverageDetails;

    @Column(nullable = false)
    private boolean active = true;

}
