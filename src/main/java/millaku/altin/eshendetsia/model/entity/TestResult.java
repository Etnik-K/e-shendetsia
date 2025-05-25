package millaku.altin.eshendetsia.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

/**
 * Klasa qe perfaqeson rezultatet e testeve mjekesore ne sistem.
 * Permban te dhenat e testeve te kryera nga mjeku per pacientin.
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "test_result_table")
public class TestResult {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    /**
     * Identifikuesi i mjekut qe ka kryer testin
     */
    @ManyToOne(optional = false)
    @JoinColumn(name = "doctor_id", nullable = false)
    private Doctor doctor;

    /**
     * Lloji i testit mjekesor
     */
    @Column(nullable=false)
    private String type;

    /**
     * Rezultati i testit mjekesor
     */
    @Column(nullable=false)
    private String result;

    /**
     * Shenime shtese per rezultatin e testit
     */
    @Column
    private String notes;

    /**
     * Data dhe ora kur eshte kryer testi
     */
    @Column(nullable = false)
    private LocalDateTime timestamp;

    @PrePersist
    public void prePersist() {
        if (timestamp == null)
            timestamp = LocalDateTime.now();
    }
}