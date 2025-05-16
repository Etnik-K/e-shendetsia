package edu.unipr.eshendetsia.model.entity;

import edu.unipr.eshendetsia.util.HttpMethod;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "permissions")
public class Permissions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @Enumerated(EnumType.STRING)
    private HttpMethod httpMethod;

    @Column
    private String resourceUrl;

    @ManyToMany(mappedBy = "permissions")
    private Set<Role> roles;
}