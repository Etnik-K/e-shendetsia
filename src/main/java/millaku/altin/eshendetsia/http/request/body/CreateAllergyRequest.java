package millaku.altin.eshendetsia.http.request.body;

import millaku.altin.eshendetsia.model.entity.Allergy;

public record CreateAllergyRequest(
        Long id,
        String allergen,
        String notes
) {
    public Allergy toAllergy() {
        return new Allergy(id, null, allergen, notes);
    }
}
