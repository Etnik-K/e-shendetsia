package edu.unipr.eshendetsia.controller;

import edu.unipr.eshendetsia.model.entity.Bill;
import edu.unipr.eshendetsia.service.implementation.BillServiceImplementation;
import edu.unipr.eshendetsia.service.interfaces.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bills")
public class BillingController {

        private final BillServiceImplementation billServiceImplementation;

        @Autowired
        public BillingController(BillServiceImplementation billServiceImplementation) {
            this.billServiceImplementation = billServiceImplementation;
        }

    @PostMapping
    public ResponseEntity<Bill> create(@RequestBody Bill bill) {
        return ResponseEntity.ok(billServiceImplementation.save(bill));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Bill>> getByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(billServiceImplementation.getByUser(userId));
    }

    @GetMapping("/status")
    public ResponseEntity<List<Bill>> getByStatus(@RequestParam boolean paid) {
        return ResponseEntity.ok(billServiceImplementation.getByPaymentStatus(paid));
    }

    @PutMapping("/{id}/pay")
    public ResponseEntity<Void> markAsPaid(@PathVariable Long id) {
        billServiceImplementation.markAsPaid(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        billServiceImplementation.delete(id);
        return ResponseEntity.noContent().build();
    }
}
