package edu.unipr.eshendetsia.model.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.Set;

/**
 * Klasa Role perfaqeson rolin e perdoruesit ne sistem.
 * Permban informacionin bazik si id dhe emer, si dhe
 * lidhjet me te drejtat e perdoruesit dhe perdoruesit qe kane kete rol.
 */
@Getter
@Entity
@Table(name = "role_table")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToMany
    @JoinTable(
        name = "role_permissions",
        joinColumns = @JoinColumn(name = "role_id"),
        inverseJoinColumns = @JoinColumn(name = "permission_id")
    )
    private Set<Permissions> permissions;

    @ManyToMany(mappedBy = "roles")
    private Set<User> users;
}