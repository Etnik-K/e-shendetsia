/**
 * Ky file permban logjiken e kontrollerit per menaxhimin e recetave
 * Ne kete kontroller implementohen te gjitha metodat qe kane te bejne me recetat
 */
package edu.unipr.eshendetsia.controller;

import edu.unipr.eshendetsia.controller.base.BaseController;
import edu.unipr.eshendetsia.http.response.ApiResponse;
import edu.unipr.eshendetsia.model.entity.Perscription;
import edu.unipr.eshendetsia.service.interfaces.PerscriptionService;
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
public class PerscriptionController extends BaseController {

    private final PerscriptionService perscriptionService;

    public PerscriptionController(PerscriptionService perscriptionService) {
        this.perscriptionService = perscriptionService;
    }

    /**
     * Krijon nje recete te re
     *
     * @param prescription receta qe do te krijohet
     * @return receta e krijuar dhe ruajtur ne databaze
     */
    @PostMapping
    public ResponseEntity<ApiResponse<Perscription>> create(@RequestBody Perscription prescription) {
        Perscription saved = perscriptionService.save(prescription);
        return this.ok(saved);
    }
}
