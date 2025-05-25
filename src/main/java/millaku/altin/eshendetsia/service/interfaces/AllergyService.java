package millaku.altin.eshendetsia.service.interfaces;

import millaku.altin.eshendetsia.model.entity.Allergy;

import java.util.List;

public interface AllergyService {
    Allergy save(Allergy allergy);

    List<Allergy> getByUserId(Long userId);

    void delete(Long id);
}
