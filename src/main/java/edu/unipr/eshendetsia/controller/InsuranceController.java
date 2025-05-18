package edu.unipr.eshendetsia.controller;


import edu.unipr.eshendetsia.model.entity.Insurance;
import edu.unipr.eshendetsia.repository.InsuranceRepository;
import edu.unipr.eshendetsia.service.implementation.InsuranceServiceImplementation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Getter
@Setter
@RestController
@RequestMapping("/insurance")
/**
 * Kontrolleri per menaxhimin e sigurimeve shendetesore.
 * Mundeson krijimin, leximin, perditesimin dhe fshirjen e sigurimeve.
 */
public class InsuranceController {
    private final InsuranceServiceImplementation service;

    @Autowired
    public InsuranceController(InsuranceServiceImplementation service) {
        this.service = service;
    }

    @PostMapping
    /**
     * Krijon nje sigurim te ri shendetsor
     * @param insurance sigurimi per tu krijuar
     * @return sigurimi i krijuar
     */
    public ResponseEntity<Insurance> create(@RequestBody Insurance insurance) {
        return ResponseEntity.ok(service.save(insurance));
    }

    @GetMapping("/user/{userId}")
    /**
     * Merr listen e sigurimeve per nje perdorues
     * @param userId ID e perdoruesit
     * @return lista e sigurimeve te perdoruesit
     */
    public ResponseEntity<List<Insurance>> getByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(service.getByUserId(userId));
    }

    @PutMapping("/{id}/status")
    /**
     * Perditeson statusin e nje sigurimi
     * @param id ID e sigurimit
     * @param active statusi i ri
     * @return sigurimi i perditesuar
     */
    public ResponseEntity<Insurance> updateStatus(@PathVariable Long id, @RequestParam boolean active) {
        Insurance updated = service.updateStatus(id, active);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    /**
     * Fshin nje sigurim
     * @param id ID e sigurimit per tu fshire
     * @return pergjigjja bosh ne rast suksesi
     */
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
