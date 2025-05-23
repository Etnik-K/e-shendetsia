package edu.unipr.eshendetsia.service.implementation;

import edu.unipr.eshendetsia.model.entity.Bill;
import edu.unipr.eshendetsia.repository.BillRepository;
import edu.unipr.eshendetsia.service.interfaces.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementimi i sherbimit per faturat
 * Menaxhon operacionet CRUD per faturat
 */
@Service
public class BillServiceImplementation implements BillService {

    private final BillRepository billRepository;

    /**
     * Konstruktori per krijimin e instances
     *
     * @param billRepository repository i faturave
     */
    @Autowired
    public BillServiceImplementation(BillRepository billRepository) {
        this.billRepository = billRepository;
    }

    /**
     * Ruan faturen ne databaze
     *
     * @param bill fatura qe do te ruhet
     * @return fatura e ruajtur
     */
    public Bill save(Bill bill) {
        return billRepository.save(bill);
    }

    /**
     * Merr te gjitha faturat e nje perdoruesi
     *
     * @param userId ID e perdoruesit
     * @return lista e faturave te perdoruesit
     */
    public List<Bill> getByUser(Long userId) {
        return billRepository.findByUserId(userId);
    }

    /**
     * Merr faturat sipas statusit te pageses
     *
     * @param isPaid statusi i pageses
     * @return lista e faturave sipas statusit
     */
    public List<Bill> getByPaymentStatus(boolean isPaid) {
        return billRepository.findByIsPaid(isPaid);
    }

    /**
     * Shenon faturen si te paguar
     *
     * @param id ID e fatures
     */
    public void markAsPaid(Long id) {
        Bill bill = billRepository.findById(id).orElse(null);
        if (bill != null) {
            bill.setPaid(true);
            billRepository.save(bill);
        }
    }

    /**
     * Fshin faturen nga databaza
     *
     * @param id ID e fatures per fshirje
     */
    public void delete(Long id) {
        billRepository.deleteById(id);
    }
}
