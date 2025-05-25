package millaku.altin.eshendetsia.http.request.body;

import millaku.altin.eshendetsia.model.entity.EmergencyContact;
import millaku.altin.eshendetsia.model.entity.User;

public record CreateEmergencyContactRequest (
        Long id,
        Long userId,
        String relation
) {
    public EmergencyContact toEmergencyContact() {
        User user = new User();
        user.setId(userId);

        return new EmergencyContact(id, user, relation);
    }
}
