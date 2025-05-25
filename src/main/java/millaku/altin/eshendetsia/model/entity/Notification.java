package millaku.altin.eshendetsia.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * Entiteti i njoftimeve ne sistem
 * Perfshin informacionin per njoftimet e perdoruesve
 */
@Data
@Entity
@Table(name = "notification_table")
public class Notification {

    @Id
    @GeneratedValue
    private Long id;

    /**
     * Identifikuesi i perdoruesit qe do te marre njoftimin
     */
    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

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
    @Column(nullable = false)
    private LocalDateTime timestamp;

    @PrePersist
    public void prePersist() {
        if (timestamp == null)
            timestamp = LocalDateTime.now();
    }

}
