package edu.unipr.eshendetsia.controller.concrete;


import edu.unipr.eshendetsia.controller.BaseController;
import edu.unipr.eshendetsia.http.request.body.CreateInsuranceRequest;
import edu.unipr.eshendetsia.model.entity.Insurance;
import edu.unipr.eshendetsia.service.interfaces.InsuranceService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Kontrolleri per menaxhimin e sigurimeve shendetesore.
 * Mundeson krijimin, leximin, perditesimin dhe fshirjen e sigurimeve.
 */
@AllArgsConstructor
@RestController
@RequestMapping("/insurance")
public class InsuranceController extends BaseController {

    private final InsuranceService insuranceService;

    /**
     * Krijon nje sigurim te ri shendetsor
     * @param insuranceRequest sigurimi per tu krijuar
     * @return sigurimi i krijuar
     */
    @PostMapping
    public ResponseEntity<String> create(@RequestBody CreateInsuranceRequest insuranceRequest, @RequestHeader("Authorization") String authHeader) {
        this.insuranceService.save(insuranceRequest.toInsurance(), authHeader);
        return this.ok("Kompania e sigurimit u krijua me sukses");
    }

    /**
     * Merr listen e sigurimeve per nje perdorues
     * @param userId ID e perdoruesit
     * @return lista e sigurimeve te perdoruesit
     */
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Insurance>> getByUser(@PathVariable Long userId, @RequestHeader("Authorization") String authHeader) {
        return this.ok(insuranceService.getByUserId(userId, authHeader));
    }

    /**
     * Perditeson statusin e nje sigurimi
     * @param id ID e sigurimit
     * @param active statusi i ri
     * @return sigurimi i perditesuar
     */
    @PutMapping("/{id}/status")
    public ResponseEntity<String> updateStatus(@PathVariable Long id, @RequestParam Boolean active, @RequestHeader("Authorization") String authHeader) {
        this.insuranceService.updateStatus(id, active, authHeader);
        return this.ok("Statusi i sigurimit u perditesuar me sukses");
    }

    /**
     * Fshin nje sigurim
     * @param id ID e sigurimit per tu fshire
     * @return pergjigjja bosh ne rast suksesi
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id, @RequestHeader("Authorization") String authHeader) {
        this.insuranceService.delete(id, authHeader);
        return this.ok("Perdoruesi u fshi me sukses");
    }
}
