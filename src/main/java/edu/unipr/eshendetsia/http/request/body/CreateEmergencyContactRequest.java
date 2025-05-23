package edu.unipr.eshendetsia.http.request.body;

import edu.unipr.eshendetsia.model.entity.Doctor;
import edu.unipr.eshendetsia.model.entity.EmergencyContact;
import edu.unipr.eshendetsia.model.entity.User;

public record CreateEmergencyContactRequest (Long id, Long userId, String name, String phone, String relation) {
    public EmergencyContact toEmergencyContact() {
        User user = new User();

        user.setId(userId);

        return new EmergencyContact(id, user, relation);
    }
}
