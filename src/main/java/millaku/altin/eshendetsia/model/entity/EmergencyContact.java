package millaku.altin.eshendetsia.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entiteti qe perfaqeson kontaktin emergjent te pacientit
 * Permban informacionet kryesore te personit qe duhet kontaktuar ne raste emergjente
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "emergency_contact_table")
public class EmergencyContact {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column
    private String relationship;
}
