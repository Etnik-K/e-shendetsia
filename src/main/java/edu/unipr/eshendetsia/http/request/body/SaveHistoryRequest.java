package edu.unipr.eshendetsia.http.request.body;

import edu.unipr.eshendetsia.model.entity.History;

import java.time.LocalDateTime;

public record SaveHistoryRequest(
    Long id, Long userId, Long doctorId, String description, String diagnosis, String treatment, LocalDateTime date
) {
    public History toHistory() {
        return new History(id, userId, doctorId, description, diagnosis, treatment, date);
    }
}
