package app.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "doctor")
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int clinicId;

    @Column(nullable = false)
    private String profesioni;

    @Column(nullable = false)
    private String licensa;

    @Column(nullable = false)
    private int experinceYears;

    public Doctor() {}

    public Doctor(Long id, int clinicId, String profesioni, String licensa, int experinceYears) {
        this.id = id;
        this.clinicId = clinicId;
        this.profesioni = profesioni;
        this.licensa = licensa;
        this.experinceYears = experinceYears;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public int getClinicId() {
        return clinicId;
    }

    public String getProfesioni() {
        return profesioni;
    }

    public String getLicensa() {
        return licensa;
    }

    public int getexperinceYears() {
        return experinceYears;
    }

}
