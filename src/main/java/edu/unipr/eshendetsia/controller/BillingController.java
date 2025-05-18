package edu.unipr.eshendetsia.controller;

import edu.unipr.eshendetsia.model.entity.Bill;
import edu.unipr.eshendetsia.service.implementation.BillServiceImplementation;
import edu.unipr.eshendetsia.service.interfaces.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Kontrolluesi per menaxhimin e faturave ne sistem
 * Ofron funksionalitete per krijimin, leximin, perditesimin dhe fshirjen e faturave
 */
@RestController
@RequestMapping("/bills")
public class BillingController {

        private final BillServiceImplementation billServiceImplementation;

        @Autowired
        public BillingController(BillServiceImplementation billServiceImplementation) {
            this.billServiceImplementation = billServiceImplementation;
        }

    /**
     * Krijon nje fature te re
     *
     * @param bill Fatura per tu krijuar
     * @return Fatura e krijuar
     */
    @PostMapping
    public ResponseEntity<Bill> create(@RequestBody Bill bill) {
        return ResponseEntity.ok(billServiceImplementation.save(bill));
    }

    /**
     * Merr te gjitha faturat e nje perdoruesi
     *
     * @param userId ID e perdoruesit
     * @return Lista e faturave
     */
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Bill>> getByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(billServiceImplementation.getByUser(userId));
    }

    /**
     * Merr faturat sipas statusit te pageses
     *
     * @param paid Statusi i pageses
     * @return Lista e faturave
     */
    @GetMapping("/status")
    public ResponseEntity<List<Bill>> getByStatus(@RequestParam boolean paid) {
        return ResponseEntity.ok(billServiceImplementation.getByPaymentStatus(paid));
    }

    /**
     * Shenon nje fature si te paguar
     *
     * @param id ID e fatures
     * @return Pergjigje bosh
     */
    @PutMapping("/{id}/pay")
    public ResponseEntity<Void> markAsPaid(@PathVariable Long id) {
        billServiceImplementation.markAsPaid(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Fshin nje fature
     *
     * @param id ID e fatures per tu fshire
     * @return Pergjigje bosh
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        billServiceImplementation.delete(id);
        return ResponseEntity.noContent().build();
    }
}
