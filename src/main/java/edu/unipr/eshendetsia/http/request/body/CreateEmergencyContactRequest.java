package edu.unipr.eshendetsia.http.request.body;

import edu.unipr.eshendetsia.model.EmergencyContact;

public record CreateEmergencyContactRequest (Long id, Long userId, String name, String phone, String relation) {
    public EmergencyContact toEmergencyContact() {
        return new EmergencyContact(id, userId, name, phone, relation);
    }
}
