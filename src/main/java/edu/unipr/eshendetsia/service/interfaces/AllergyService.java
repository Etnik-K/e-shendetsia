package edu.unipr.eshendetsia.service.interfaces;

import edu.unipr.eshendetsia.model.entity.Allergy;

import java.util.List;

public interface AllergyService {
    public Allergy save(Allergy allergy);

    public List<Allergy> getByUserId(Long userId);

    public void delete(Long id);
}
