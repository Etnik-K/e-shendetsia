package edu.unipr.eshendetsia.controller.concrete;

import com.auth0.jwt.exceptions.JWTVerificationException;
import edu.unipr.eshendetsia.controller.BaseController;
import edu.unipr.eshendetsia.exception.concrete.NotFoundException;
import edu.unipr.eshendetsia.exception.concrete.UnauthorizedException;
import edu.unipr.eshendetsia.model.entity.UserAllergy;
import edu.unipr.eshendetsia.service.interfaces.UserAllergyService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("user_allergies")
public class UserAllergyController extends BaseController {

    private final UserAllergyService userAllergyService;
    /**
     * Merr listen e alergjive per nje pacient specifik
     *
     * @param userId ID e pacientit
     * @return Lista e alergjive te pacientit
     */
    @GetMapping("/{userId}")
    public ResponseEntity<List<UserAllergy>> getByUser(@PathVariable Long userId, @RequestHeader("Authorization") String authHeader) throws JWTVerificationException, UnauthorizedException, NotFoundException {
        return this.ok(this.userAllergyService.getByUserId(userId, authHeader));
    }

    /**
     * Fshin nje alergji nga sistemi
     *
     * @param userAllergyId ID e alergjise qe do te fshihet
     * @return Pergjigje pa permbajtje
     */
    @DeleteMapping("/{userAllergyId}")
    public ResponseEntity<String> delete(@PathVariable Long userAllergyId, @RequestHeader("Authorization") String authHeader) throws NotFoundException, UnauthorizedException {
        this.userAllergyService.delete(userAllergyId, authHeader);
        return this.ok("Alergjia u fshi me sukses");
    }
}
