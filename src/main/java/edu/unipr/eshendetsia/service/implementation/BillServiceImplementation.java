package edu.unipr.eshendetsia.service.implementation;

import edu.unipr.eshendetsia.model.entity.Bill;
import edu.unipr.eshendetsia.repository.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillServiceImplementation {

    private final BillRepository billRepository;

    @Autowired
    public BillServiceImplementation(BillRepository billRepository) {
        this.billRepository = billRepository;
    }

    public Bill save(Bill bill) {
        return billRepository.save(bill);
    }

    public List<Bill> getByUser(Long userId) {
        return billRepository.findByUserId(userId);
    }

    public List<Bill> getByPaymentStatus(boolean isPaid) {
        return billRepository.findByIsPaid(isPaid);
    }

    public void markAsPaid(Long id) {
        Bill bill = billRepository.findById(id).orElse(null);
        if (bill != null) {
            bill.setPaid(true);
            billRepository.save(bill);
        }
    }

    public void delete(Long id) {
        billRepository.deleteById(id);
    }
}
