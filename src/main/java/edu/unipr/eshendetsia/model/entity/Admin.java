package edu.unipr.eshendetsia.model.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "admin_table")
public class Admin {
    @Id
    private Long id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "id")
    private User user;
}