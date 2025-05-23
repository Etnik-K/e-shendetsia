package edu.unipr.eshendetsia.controller.concrete;

import com.auth0.jwt.exceptions.JWTVerificationException;
import edu.unipr.eshendetsia.controller.BaseController;
import edu.unipr.eshendetsia.exception.concrete.UnauthorizedException;
import edu.unipr.eshendetsia.http.request.body.CreateAllergyRequest;
import edu.unipr.eshendetsia.model.entity.Allergy;
import edu.unipr.eshendetsia.service.interfaces.AllergyService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Kontrolluesi per menaxhimin e alergjive te pacienteve
 * Sherben si nderfaqe API per operacione CRUD te alergjive
 */
@AllArgsConstructor
@RestController
@RequestMapping("/allergies")
public class AllergyController extends BaseController {

    private final AllergyService allergyService;

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
}
