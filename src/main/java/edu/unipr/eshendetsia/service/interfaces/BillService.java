package edu.unipr.eshendetsia.service.interfaces;

import edu.unipr.eshendetsia.model.entity.Bill;

import java.util.List;

public interface BillService {

    Bill save(Bill bill);

    List<Bill> getByUser(Long userId);

    List<Bill> getByPaymentStatus(boolean isPaid);

    void markAsPaid(Long id);

    void delete(Long id);

}
