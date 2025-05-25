package millaku.altin.eshendetsia.model.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

/**
 * Klasa qe perfaqeson informacionin e alergjeneve te pacientit
 * Permban informacionin per tipin e alergjise dhe reaksionet perkatese
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "allergy_table")
public class Allergy {

    @Id
    @GeneratedValue
    private Long id;

    @OneToMany(mappedBy = "allergy", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<UserAllergy> users;

    /**
     * Substanca apo medikamenti ndaj te cilit pacienti ka alergji
     */
    @Column(nullable = false)
    private String allergen;

    @Lob
    private String extraNotes;
}
