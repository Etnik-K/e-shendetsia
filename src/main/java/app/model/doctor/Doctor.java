package app.model.doctor;

import jakarta.persistence.*;

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

    public Doctor() {}

    public long getId() {
        return id;
    }

    public long getClinicId() {
        return clinicId;
    }

    public String getProfesioni() {
        return profesioni;
    }

    public String getLicensa() {
        return licensa;
    }
}
