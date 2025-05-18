package edu.unipr.eshendetsia.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

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

    @Column(nullable=false)
    private Long doctorId;

    @Column(nullable=false)
    private String type;

    @Column(nullable=false)
    private String result;

    @Column
    private String notes;

    @Column
    private LocalDateTime timestamp;
}