package app.model.doctor;

import app.model.clinic.Clinic;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.Set;

@Getter
@Entity
@Table(name = "doctor")
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToMany
    @JoinTable(
            name = "employed_by",
            joinColumns = @JoinColumn(name = "doctor_id"),
            inverseJoinColumns = @JoinColumn(name = "clinic_id")
    )
    private Set<Clinic> employedBy;

    @Column(nullable = false)
    private String profesioni;

    @Column(nullable = false)
    private String licensa;

}