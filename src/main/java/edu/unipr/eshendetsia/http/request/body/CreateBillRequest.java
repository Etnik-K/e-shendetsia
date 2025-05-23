package edu.unipr.eshendetsia.http.request.body;

import edu.unipr.eshendetsia.model.entity.Bill;
import edu.unipr.eshendetsia.model.entity.Doctor;
import edu.unipr.eshendetsia.model.entity.User;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record CreateBillRequest(
    Long id, Long userId, Double amount, String description, boolean isPaid, LocalDateTime issuedAt
) {
    public Bill toBill() {
        User user = new User();

        user.setId(userId);

        return new Bill(id, user, BigDecimal.valueOf(amount), description, isPaid, issuedAt);
    }
}