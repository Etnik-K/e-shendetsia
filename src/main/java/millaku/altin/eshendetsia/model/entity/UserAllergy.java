package millaku.altin.eshendetsia.model.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import millaku.altin.eshendetsia.model.compositekey.UserAllergyId;
import jakarta.persistence.*;
import lombok.Data;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "user_allergy_table")
public class UserAllergy {

    @EmbeddedId
    private UserAllergyId id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "allergy_id", nullable = false)
    private Allergy allergy;

    @Column(nullable = false)
    private String reaction;

    @Lob
    private String notes;

}
