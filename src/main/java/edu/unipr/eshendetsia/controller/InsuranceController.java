package edu.unipr.eshendetsia.controller;


import edu.unipr.eshendetsia.model.entity.Insurance;
import edu.unipr.eshendetsia.service.interfaces.InsuranceService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Kontrolleri per menaxhimin e sigurimeve shendetesore.
 * Mundeson krijimin, leximin, perditesimin dhe fshirjen e sigurimeve.
 */
@Getter
@Setter
@RestController
@RequestMapping("/insurance")
public class InsuranceController {
    private final InsuranceService insuranceService;

    @Autowired
    public InsuranceController(InsuranceService insuranceService) {
        this.insuranceService = insuranceService;
    }

    /**
     * Krijon nje sigurim te ri shendetsor
     * @param insurance sigurimi per tu krijuar
     * @return sigurimi i krijuar
     */
    @PostMapping
    public ResponseEntity<Insurance> create(@RequestBody Insurance insurance) {
        return ResponseEntity.ok(insuranceService.save(insurance));
    }

    /**
     * Merr listen e sigurimeve per nje perdorues
     * @param userId ID e perdoruesit
     * @return lista e sigurimeve te perdoruesit
     */
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Insurance>> getByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(insuranceService.getByUserId(userId));
    }

    /**
     * Perditeson statusin e nje sigurimi
     * @param id ID e sigurimit
     * @param active statusi i ri
     * @return sigurimi i perditesuar
     */
    @PutMapping("/{id}/status")
    public ResponseEntity<Insurance> updateStatus(@PathVariable Long id, @RequestParam boolean active) {
        Insurance updated = insuranceService.updateStatus(id, active);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    /**
     * Fshin nje sigurim
     * @param id ID e sigurimit per tu fshire
     * @return pergjigjja bosh ne rast suksesi
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        insuranceService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
