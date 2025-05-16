package edu.unipr.eshendetsia.controller;

import edu.unipr.eshendetsia.model.entity.Perscription;
import edu.unipr.eshendetsia.repository.PerscriptionRepository;
import edu.unipr.eshendetsia.service.implementation.PerscriptionServiceImplementation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/perscriptions")
public class PerscriptionController {

    private final PerscriptionServiceImplementation perscriptionService;

    public PerscriptionController(PerscriptionServiceImplementation perscriptionService) {
        this.perscriptionService = perscriptionService;
    }

    @PostMapping
    public ResponseEntity<Perscription> create(@RequestBody Perscription prescription) {
        Perscription saved = perscriptionService.save(prescription);
        return ResponseEntity.ok(saved);
    }
}
