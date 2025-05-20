package edu.unipr.eshendetsia.http.request;

import edu.unipr.eshendetsia.model.entity.Allergy;

public record CreateAllergyRequest(
        Long id,
        Long userId,
        String allergen,
        String reaction,
        String notes
) {
    public Allergy toAllergy() {
        return new Allergy(id, userId, allergen, reaction, notes);
    }
}
