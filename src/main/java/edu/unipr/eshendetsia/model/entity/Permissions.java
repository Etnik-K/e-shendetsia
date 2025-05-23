package edu.unipr.eshendetsia.model.entity;

import edu.unipr.eshendetsia.model.enums.HttpMethod;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.Set;

/**
 * Klasa qe perfaqeson lejet ne sistem.
 * Permban te gjitha lejet qe mund te jepen per perdoruesit.
 */

@Data
@Entity
@Table(name = "permission_table")
public class Permissions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Metoda HTTP e lejuar per kete leje
     */
    @NotBlank
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private HttpMethod httpMethod;

    /**
     * URL e burimit per te cilin vlen kjo leje
     */
    @NotBlank
    @Column(nullable = false)
    private String resourceUrl;

    /**
     * Rolet qe kane kete leje
     */
    @ManyToMany(mappedBy = "permissions")
    private Set<Role> roles;
}