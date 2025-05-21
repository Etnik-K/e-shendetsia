package edu.unipr.eshendetsia.service.interfaces;

import edu.unipr.eshendetsia.model.Bill;

import java.util.List;

public interface BillService {
    public Bill save(Bill bill);
    public List<Bill> getByUser(Long userId);
    public List<Bill> getByPaymentStatus(boolean isPaid);
    public void markAsPaid(Long id);
    public void delete(Long id);
}
