package edu.unipr.eshendetsia.controller;

import edu.unipr.eshendetsia.controller.base.BaseController;
import edu.unipr.eshendetsia.http.response.ApiResponse;
import edu.unipr.eshendetsia.model.entity.Allergy;
import edu.unipr.eshendetsia.service.interfaces.AllergyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Kontrolluesi per menaxhimin e alergjive te pacienteve
 * Sherben si nderfaqe API per operacione CRUD te alergjive
 */
@RestController
@RequestMapping("/allergies")
public class AllergyController extends BaseController {

    private final AllergyService allergyService;

    @Autowired
    public AllergyController(AllergyService allergyService) {
        this.allergyService = allergyService;
    }

    /**
     * Krijon nje alergji te re per pacientin
     *
     * @param allergy Alergjia qe do te ruhet
     * @return Alergjia e krijuar me sukses
     */
    @PostMapping
    public ResponseEntity<ApiResponse<Allergy>> create(@RequestBody Allergy allergy) {
        return this.ok(allergyService.save(allergy));
    }

    /**
     * Merr listen e alergjive per nje pacient specifik
     *
     * @param userId ID e pacientit
     * @return Lista e alergjive te pacientit
     */
    @GetMapping("/user/{userId}")
    public ResponseEntity<ApiResponse<List<Allergy>>> getByUser(@PathVariable Long userId) {
        return this.ok(allergyService.getByUserId(userId));
    }

    /**
     * Fshin nje alergji nga sistemi
     *
     * @param id ID e alergjise qe do te fshihet
     * @return Pergjigje pa permbajtje
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> delete(@PathVariable Long id) {
        try{
            allergyService.delete(id);
            return this.ok("Useri u fshi me sukses");
        } catch (Exception e) {
            return this.error("Useri nuk u fshi", HttpStatus.UNAUTHORIZED);
        }
    }
}
