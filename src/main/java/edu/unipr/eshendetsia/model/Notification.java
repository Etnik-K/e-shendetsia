package edu.unipr.eshendetsia.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * Entiteti i njoftimeve ne sistem
 * Perfshin informacionin per njoftimet e perdoruesve
 */
@Data
@Entity
@Table(name = "noticiations")
public class Notification {

    @Id
    @GeneratedValue
    private Long id;

    /**
     * Identifikuesi i perdoruesit qe do te marre njoftimin
     */
    @Column(nullable = false)
    private Long userId;

    /**
     * Mesazhi i njoftimit
     */
    @Column(nullable = false)
    private String message;

    /**
     * Tregon nese njoftimi eshte lexuar
     */
    @Column
    private boolean isRead = false;

    /**
     * Koha kur eshte krijuar njoftimi
     */
    @Column
    private LocalDateTime timestamp = LocalDateTime.now();

}
