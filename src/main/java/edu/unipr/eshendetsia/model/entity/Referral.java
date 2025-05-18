package edu.unipr.eshendetsia.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Table(name = "referrals")
public class Referral {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private Long patientId;

    @Column(nullable = false)
    private Long fromDoctorId;

    @Column(nullable = false)
    private Long toDoctorId;

    @Column(nullable = false)
    private String reason;

    @Column
    private LocalDateTime referralDate = LocalDateTime.now();
}
