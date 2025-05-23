package edu.unipr.eshendetsia.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Klasa Bill perben nje fature e cila permban shenime te pagesave te pacienteve
 * ne platformen per menaxhimin e klinikave shendetesore.
 * Kjo klase perdoret per te ruajtur dhe menaxhuar te dhenat e pagesave.
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "bill_table")
public class Bill {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    /**
     * Shuma e pageses ne monedhen perkatese
     */
    @Column(nullable = false)
    private BigDecimal amount;

    /**
     * Pershkrimi i fatures dhe arsyeja e pageses
     */
    @Lob
    @Column(nullable = false)
    private String description;

    /**
     * Tregon nese fatura eshte paguar apo jo
     */
    @Column(name = "is_paid", nullable = false)
    private boolean paid = false;

    /**
     * Data dhe ora kur eshte leshuar fatura
     */
    @Column(nullable = false)
    private LocalDateTime issuedAt = LocalDateTime.now();

    @PrePersist
    public void prePersist() {
        if (issuedAt == null) {
            issuedAt = LocalDateTime.now();
        }
    }

}
