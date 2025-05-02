package app.model.doctor;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "doctor")
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private long clinicId;

    @Column(nullable = false)
    private String profesioni;

    @Column(nullable = false)
    private String licensa;

}
