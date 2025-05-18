/**
 * Ky file permban logjiken e kontrollerit per menaxhimin e recetave
 * Ne kete kontroller implementohen te gjitha metodat qe kane te bejne me recetat
 */
package edu.unipr.eshendetsia.controller;

import edu.unipr.eshendetsia.model.entity.Perscription;
import edu.unipr.eshendetsia.repository.PerscriptionRepository;
import edu.unipr.eshendetsia.service.implementation.PerscriptionServiceImplementation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Kontrolleri per menaxhimin e recetave
 * Permban logjiken e nevojshme per krijimin dhe ruajtjen e recetave te reja
 */
@RestController
@RequestMapping("/perscriptions")
public class PerscriptionController {

    private final PerscriptionServiceImplementation perscriptionService;

    public PerscriptionController(PerscriptionServiceImplementation perscriptionService) {
        this.perscriptionService = perscriptionService;
    }

    /**
     * Krijon nje recete te re
     *
     * @param prescription receta qe do te krijohet
     * @return receta e krijuar dhe ruajtur ne databaze
     */
    @PostMapping
    public ResponseEntity<Perscription> create(@RequestBody Perscription prescription) {
        Perscription saved = perscriptionService.save(prescription);
        return ResponseEntity.ok(saved);
    }
}
