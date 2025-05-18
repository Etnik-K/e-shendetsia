package edu.unipr.eshendetsia.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * Klasa Bill perben nje fature e cila permban shenime te pagesave te pacienteve
 * ne platformen per menaxhimin e klinikave shendetesore.
 * Kjo klase perdoret per te ruajtur dhe menaxhuar te dhenat e pagesave.
 */
@Getter
@Setter
@Entity
@Table(name = "bills")
public class Bill {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private Long userId;

    /**
     * Shuma e pageses ne monedhen perkatese
     */
    @Column(nullable = false)
    private Double amount;

    /**
     * Pershkrimi i fatures dhe arsyeja e pageses
     */
    @Column(nullable = false)
    private String description;

    /**
     * Tregon nese fatura eshte paguar apo jo
     */
    @Column
    private boolean isPaid = false;

    /**
     * Data dhe ora kur eshte leshuar fatura
     */
    @Column
    private LocalDateTime issuedAt = LocalDateTime.now();

}
