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
public class InsuranceController {
    private final InsuranceServiceImplementation service;

    @Autowired
    public InsuranceController(InsuranceServiceImplementation service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Insurance> create(@RequestBody Insurance insurance) {
        return ResponseEntity.ok(service.save(insurance));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Insurance>> getByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(service.getByUserId(userId));
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<Insurance> updateStatus(@PathVariable Long id, @RequestParam boolean active) {
        Insurance updated = service.updateStatus(id, active);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
