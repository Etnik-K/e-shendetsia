package edu.unipr.eshendetsia.http.request.body;

import edu.unipr.eshendetsia.model.Perscription;

import java.time.LocalDateTime;

public record CreatePerscriptionRequest(
    Long id, Long userId, Long doctorId, String medication, String dosage, String frequency, String durage, LocalDateTime issuedAt
) {
    public Perscription toPerscription() {
        return new Perscription(id, userId, doctorId, medication, dosage, frequency, durage, issuedAt);
    }
}
