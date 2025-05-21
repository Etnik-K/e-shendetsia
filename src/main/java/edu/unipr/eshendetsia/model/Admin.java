package edu.unipr.eshendetsia.model;

import jakarta.persistence.*;

/**
 * Klasa Admin perfaqeson nje administrator ne sistem
 * Lidhet me nje llogari perdoruesi
 */
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