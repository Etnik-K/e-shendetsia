package edu.unipr.eshendetsia.service.interfaces;

import edu.unipr.eshendetsia.model.entity.Bill;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BillService {
    public Bill save(Bill bill);
    public List<Bill> getByUser(Long userId);
    public List<Bill> getByPaymentStatus(boolean isPaid);
    public void markAsPaid(Long id);
    public void delete(Long id);
}
