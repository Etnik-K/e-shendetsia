package edu.unipr.eshendetsia.model.entity;

import edu.unipr.eshendetsia.http.HttpMethod;
import jakarta.persistence.*;

import java.util.Set;

/**
 * Klasa qe perfaqeson lejet ne sistem.
 * Permban te gjitha lejet qe mund te jepen per perdoruesit.
 */
@Entity
@Table(name = "permissions")
public class Permissions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    /**
     * Metoda HTTP e lejuar per kete leje
     */
    @Enumerated(EnumType.STRING)
    private HttpMethod httpMethod;

    /**
     * URL e burimit per te cilin vlen kjo leje
     */
    @Column
    private String resourceUrl;

    /**
     * Rolet qe kane kete leje
     */
    @ManyToMany(mappedBy = "permissions")
    private Set<Role> roles;
}