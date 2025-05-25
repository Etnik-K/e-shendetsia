package millaku.altin.eshendetsia.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.util.Set;

/**
 * Kjo klase perfaqeson nje klinike ne sistemin e shendetesise.
 * <p>
 * Permban informacionet kryesore per kliniken:
 * - drejtorin e klinikes
 * - doktoret e punesuar
 * - adresen e klinikes
 * - emailin zyrtar
 * - numrin e telefonit
 * - faqen e internetit
 * <p>
 * Te gjithe fushat jane te detyrueshme per tu plotesuar.
 */
@Data
@Entity
@Table(name = "clinic_table")
public class Clinic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "drejtori_id")
    private User drejtori;

    @ManyToMany(mappedBy = "employedBy")
    private Set<Doctor> employed;

    @NotBlank(message = "Address is required")
    @Column(nullable = false)
    private String address;

    @Email(message = "Invalid email format")
    @NotBlank(message = "Email is required")
    @Column(nullable = false, unique = true)
    private String email;

    @Pattern(regexp = "^\\+?[1-9]\\d{1,14}$", message = "Invalid phone number")
    @Column(nullable = false)
    private String phone;

    @NotBlank(message = "Website is required")
    @Column(nullable = false)
    private String website;
}