package app.model.clinic;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "clinics")
public class Clinic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String drejtori;

    @Column(nullable = false)
    public String address;

    @Column(nullable = false)
    public String email;

    @Column(nullable = false)
    public int phone;

    @Column(nullable = false)
    public String website;

}
