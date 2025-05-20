package edu.unipr.eshendetsia.http.request;

import edu.unipr.eshendetsia.model.entity.Bill;

import java.time.LocalDateTime;

public record CreateBillRequest(
    Long id, Long userId, Double amount, String description, boolean isPaid, LocalDateTime issuedAt
) {
    public Bill toBill() {
        return new Bill(id, userId, amount, description, isPaid, issuedAt);
    }
}