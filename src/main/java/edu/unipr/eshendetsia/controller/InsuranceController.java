package edu.unipr.eshendetsia.controller;


import com.auth0.jwt.exceptions.JWTVerificationException;
import edu.unipr.eshendetsia.controller.base.BaseController;
import edu.unipr.eshendetsia.exception.NotFoundException;
import edu.unipr.eshendetsia.exception.UnauthorizedException;
import edu.unipr.eshendetsia.http.request.body.CreateInsuranceRequest;
import edu.unipr.eshendetsia.http.response.ApiResponse;
import edu.unipr.eshendetsia.model.Insurance;
import edu.unipr.eshendetsia.service.interfaces.InsuranceService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Kontrolleri per menaxhimin e sigurimeve shendetesore.
 * Mundeson krijimin, leximin, perditesimin dhe fshirjen e sigurimeve.
 */
@Getter
@Setter
@RestController
@RequestMapping("/insurance")
public class InsuranceController extends BaseController {
    private final InsuranceService insuranceService;

    @Autowired
    public InsuranceController(InsuranceService insuranceService) {
        this.insuranceService = insuranceService;
    }

    /**
     * Krijon nje sigurim te ri shendetsor
     * @param insuranceRequest sigurimi per tu krijuar
     * @return sigurimi i krijuar
     */
    @PostMapping
    public ResponseEntity<ApiResponse<String>> create(@RequestBody CreateInsuranceRequest insuranceRequest) {
        try{
            insuranceService.save(insuranceRequest.toInsurance());
            return this.ok("Kompania e sigurimit u krijua me sukses");
        } catch (JWTVerificationException | UnauthorizedException exception) {
            return this.error("Nuk jeni i autorizuar", HttpStatus.UNAUTHORIZED);
        }
    }

    /**
     * Merr listen e sigurimeve per nje perdorues
     * @param userId ID e perdoruesit
     * @return lista e sigurimeve te perdoruesit
     */
    @GetMapping("/user/{userId}")
    public ResponseEntity<ApiResponse<List<Insurance>>> getByUser(@PathVariable Long userId) {
        try{
            return this.ok(insuranceService.getByUserId(userId));
        } catch (JWTVerificationException  | UnauthorizedException e) {
            return this.error("Nuk jeni i autorizuar!", HttpStatus.UNAUTHORIZED);
        } catch (NotFoundException e) {
            return this.error("Perdoruesi nuk u gjet", HttpStatus.UNAUTHORIZED);
        }
    }

    /**
     * Perditeson statusin e nje sigurimi
     * @param id ID e sigurimit
     * @param active statusi i ri
     * @return sigurimi i perditesuar
     */
    @PutMapping("/{id}/status")
    public ResponseEntity<ApiResponse<String>> updateStatus(@PathVariable Long id, @RequestParam Boolean active) {
        try{
            insuranceService.updateStatus(id, active);
            return this.ok("Statusi i sigurimit u perditesuar me sukses");
        } catch (JWTVerificationException | UnauthorizedException exception) {
            return this.error("Nuk jeni i autorizuar", HttpStatus.UNAUTHORIZED);
        } catch (NotFoundException e) {
            return this.error("Sigurimi nuk u gjet", HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Fshin nje sigurim
     * @param id ID e sigurimit per tu fshire
     * @return pergjigjja bosh ne rast suksesi
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> delete(@PathVariable Long id) {
        try{
            insuranceService.delete(id);
            return this.ok("Perdoruesi u fshi me sukses");
        } catch (JWTVerificationException | UnauthorizedException exception) {
            return this.error("Nuk jeni i autorizuar", HttpStatus.UNAUTHORIZED);
        }
    }
}
