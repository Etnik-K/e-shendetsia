package edu.unipr.eshendetsia.controller;

import com.auth0.jwt.exceptions.JWTVerificationException;
import edu.unipr.eshendetsia.controller.base.BaseController;
import edu.unipr.eshendetsia.exception.NotFoundException;
import edu.unipr.eshendetsia.exception.UnauthorizedException;
import edu.unipr.eshendetsia.http.request.body.CreateAllergyRequest;
import edu.unipr.eshendetsia.http.response.ApiResponse;
import edu.unipr.eshendetsia.model.Allergy;
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
     * @param allergyRequest Alergjia qe do te ruhet
     * @return Alergjia e krijuar me sukses
     */
    @PostMapping
    public ResponseEntity<ApiResponse<String>> create(@RequestBody CreateAllergyRequest allergyRequest) {
        try{
            allergyService.save(allergyRequest.toAllergy());
            return this.ok("Alergjia u krijua me sukses");
        } catch (UnauthorizedException | JWTVerificationException e) {
            return this.error("Nuk jeni i autorizuar", HttpStatus.UNAUTHORIZED);
        }
    }

    /**
     * Merr listen e alergjive per nje pacient specifik
     *
     * @param userId ID e pacientit
     * @return Lista e alergjive te pacientit
     */
    @GetMapping("/user/{userId}")
    public ResponseEntity<ApiResponse<List<Allergy>>> getByUser(@PathVariable Long userId) {
        try{
            return this.ok(allergyService.getByUserId(userId));
        } catch (UnauthorizedException | JWTVerificationException e) {
            return this.error("Nuk jeni i autorizuar", HttpStatus.UNAUTHORIZED);
        } catch (NotFoundException e) {
            return this.error("Useri nuk eshte gjetur", HttpStatus.NOT_FOUND);
        }
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
            return this.ok("Alergjia u fshi me sukses");
        } catch (UnauthorizedException | JWTVerificationException e) {
            return this.error("Nuk jeni i autorizuar", HttpStatus.UNAUTHORIZED);
        } catch (NotFoundException e) {
            return this.error("Alergjia nuk eshte gjetur", HttpStatus.NOT_FOUND);
        }
    }
}
