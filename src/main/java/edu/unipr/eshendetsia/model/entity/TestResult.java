package edu.unipr.eshendetsia.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * Klasa qe perfaqeson rezultatet e testeve mjekesore ne sistem.
 * Permban te dhenat e testeve te kryera nga mjeku per pacientin.
 */
@Setter
@Getter
@Entity
@Table(name = "test_results")
public class TestResult {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable=false)
    private Long userId;

    /**
     * Identifikuesi i mjekut qe ka kryer testin
     */
    @Column(nullable=false)
    private Long doctorId;

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
    @Column
    private LocalDateTime timestamp;
}