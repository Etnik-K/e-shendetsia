package edu.unipr.eshendetsia.controller.concrete;


import edu.unipr.eshendetsia.controller.BaseController;
import edu.unipr.eshendetsia.exception.concrete.NotFoundException;
import edu.unipr.eshendetsia.exception.concrete.UnauthorizedException;
import edu.unipr.eshendetsia.http.request.body.CreateBillRequest;
import edu.unipr.eshendetsia.model.entity.Bill;
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
public class BillingController extends BaseController {

    private final BillService billService;

    @Autowired
    public BillingController(BillService billService) {
            this.billService = billService;
        }

    /**
     * Krijon nje fature te re
     *
     * @param billRequest Fatura per tu krijuar
     * @return Mesazh konfirmues
     */
    @PostMapping
    public ResponseEntity<String> create(@RequestBody CreateBillRequest billRequest) throws UnauthorizedException {
            billService.save(billRequest.toBill());
            return this.ok("Faktura u krijua me sukses");
    }

    /**
     * Merr te gjitha faturat e nje perdoruesi
     *
     * @param userId ID e perdoruesit
     * @return Lista e faturave
     */
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Bill>> getByUser(@PathVariable Long userId)throws UnauthorizedException, NotFoundException {
            return this.ok(billService.getByUser(userId));
    }

    /**
     * Merr faturat sipas statusit te pageses
     *
     * @param paid Statusi i pageses
     * @return Lista e faturave
     */
    @GetMapping("/status/{paid}")
    public ResponseEntity<List<Bill>> getByStatus(@PathVariable Boolean paid) throws UnauthorizedException, NotFoundException {
            return this.ok(billService.getByPaymentStatus(paid));
    }

    /**
     * Shenon nje fature si te paguar
     *
     * @param id ID e fatures
     * @return Pergjigje bosh
     */
    @PutMapping("/{id}/pay")
    public ResponseEntity<String> markAsPaid(@PathVariable Long id) throws UnauthorizedException, NotFoundException {
            this.billService.markAsPaid(id);
            return this.ok("Eshte paguar");
        }

    /**
     * Fshin nje fature
     *
     * @param id ID e fatures per tu fshire
     * @return Pergjigje bosh
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) throws UnauthorizedException, NotFoundException {
            this.billService.delete(id);
            return this.ok("Faktura u fshi me sukses");
        }
    }