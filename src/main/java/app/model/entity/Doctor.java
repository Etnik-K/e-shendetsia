package app.model.doctor;

import app.model.clinic.Clinic;
import app.model.user.User;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.Set;

@Getter
@Entity
@Table(name = "doctor_table")
public class Doctor {

    @Id
    private Long id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "id")
    private User user;

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