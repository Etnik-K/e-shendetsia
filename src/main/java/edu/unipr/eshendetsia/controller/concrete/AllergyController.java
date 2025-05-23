package edu.unipr.eshendetsia.controller.concrete;

import com.auth0.jwt.exceptions.JWTVerificationException;
import edu.unipr.eshendetsia.controller.BaseController;
import edu.unipr.eshendetsia.exception.concrete.NotFoundException;
import edu.unipr.eshendetsia.exception.concrete.UnauthorizedException;
import edu.unipr.eshendetsia.http.request.body.CreateAllergyRequest;
import edu.unipr.eshendetsia.model.entity.Allergy;
import edu.unipr.eshendetsia.service.interfaces.AllergyService;
import edu.unipr.eshendetsia.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public AllergyController(AllergyService allergyService, UserService userService) {
        this.allergyService = allergyService;
    }

    /**
     * Krijon nje alergji te re per pacientin
     *
     * @param allergyRequest Alergjia qe do te ruhet
     * @return Alergjia e krijuar me sukses
     */
    @PostMapping
    public ResponseEntity<String> create(@RequestBody CreateAllergyRequest allergyRequest, @RequestHeader("Authorization") String authHeader)
            throws JWTVerificationException, UnauthorizedException {
        Allergy allergy = allergyRequest.toAllergy();
        allergyService.save(allergy, authHeader);
        return this.ok("Alergjia u krijua me sukses");
    }

    /**
     * Merr listen e alergjive per nje pacient specifik
     *
     * @param userId ID e pacientit
     * @return Lista e alergjive te pacientit
     */
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Allergy>> getByUser(@PathVariable Long userId, @RequestHeader("Authorization") String authHeader) throws JWTVerificationException, UnauthorizedException, NotFoundException {
        return this.ok(allergyService.getByUserId(userId, authHeader));
    }

    /**
     * Fshin nje alergji nga sistemi
     *
     * @param id ID e alergjise qe do te fshihet
     * @return Pergjigje pa permbajtje
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id, @RequestHeader("Authorization") String authHeader) {
        allergyService.delete(id, authHeader);
        return this.ok("Alergjia u fshi me sukses");
    }
}
