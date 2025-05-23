/**
 * Ky file permban logjiken e kontrollerit per menaxhimin e recetave
 * Ne kete kontroller implementohen te gjitha metodat qe kane te bejne me recetat
 */
package edu.unipr.eshendetsia.controller.concrete;

import edu.unipr.eshendetsia.controller.BaseController;
import edu.unipr.eshendetsia.http.request.body.CreatePerscriptionRequest;
import edu.unipr.eshendetsia.service.interfaces.PerscriptionService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Kontrolleri per menaxhimin e recetave
 * Permban logjiken e nevojshme per krijimin dhe ruajtjen e recetave te reja
 */
@AllArgsConstructor
@RestController
@RequestMapping("/perscriptions")
public class PerscriptionController extends BaseController {

    private final PerscriptionService perscriptionService;

    /**
     * Krijon nje recete te re
     *
     * @param prescriptionRequest receta qe do te krijohet
     * @return receta e krijuar dhe ruajtur ne databaze
     */
    @PostMapping
    public ResponseEntity<String> create(@RequestBody CreatePerscriptionRequest prescriptionRequest, @RequestHeader("Authorization") String authHeader) {
        this.perscriptionService.save(prescriptionRequest.toPerscription(), authHeader);
        return this.ok("Receta u krijua me sukses");
    }
}
