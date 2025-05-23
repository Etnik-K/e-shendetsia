package edu.unipr.eshendetsia.model.entity;

import jakarta.persistence.*;
import lombok.Data;

/**
 * Klasa Admin perfaqeson nje administrator ne sistem
 * Lidhet me nje llogari perdoruesi
 */
@Data
@Entity
@Table(name = "admin_table")
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    private User user;
}