package millaku.altin.eshendetsia.model.compositekey;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Embeddable
public class UserAllergyId implements Serializable {

    private Long user;

    private Long allergy;

}