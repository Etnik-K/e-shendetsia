package edu.unipr.eshendetsia.controller;

import edu.unipr.eshendetsia.model.entity.Referral;
import edu.unipr.eshendetsia.service.implementation.ReferralServiceImplementation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Setter
@Getter
@RestController
@RequestMapping("/referrals")
public class ReferralController {
        private final ReferralServiceImplementation service;

        @Autowired
        public ReferralController(ReferralServiceImplementation service) {
            this.service = service;
        }

    @PostMapping
    public ResponseEntity<Referral> create(@RequestBody Referral referral) {
        return ResponseEntity.ok(service.save(referral));
    }

    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<Referral>> getByPatient(@PathVariable Long patientId) {
        return ResponseEntity.ok(service.getByPatient(patientId));
    }

    @GetMapping("/doctor/{doctorId}")
    public ResponseEntity<List<Referral>> getByDoctor(@PathVariable Long doctorId) {
        return ResponseEntity.ok(service.getByReceivingDoctor(doctorId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
