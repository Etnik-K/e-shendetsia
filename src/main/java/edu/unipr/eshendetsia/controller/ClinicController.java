package edu.unipr.eshendetsia.controller;

import edu.unipr.eshendetsia.exception.NotFoundException;
import edu.unipr.eshendetsia.exception.UnauthorizedException;
import edu.unipr.eshendetsia.model.entity.Clinic;
import edu.unipr.eshendetsia.service.interfaces.ClinicService;
import edu.unipr.eshendetsia.http.response.ApiResponse;
import edu.unipr.eshendetsia.util.BaseController;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Kontrolleri qe menaxhon kerkesat HTTP per resursin e Klinikave.
 * Ofron funksionalitete per krijimin, leximin, perditesimin dhe fshirjen e klinikave.
 * Sherben si nderfaqe RESTful per menaxhimin e te dhenave te klinikave.
 */
@RestController
@RequestMapping("/clinics")
public class ClinicController extends BaseController {

    private final ClinicService clinicService;

    @Autowired
    public ClinicController(ClinicService clinicService) {
        this.clinicService = clinicService;
    }

    /**
     * Kthen listen e te gjitha klinikave
     *
     * @param authHeader tokeni i autentikimit
     * @return lista e klinikave
     */
    @GetMapping
    public ResponseEntity<ApiResponse<List<Clinic>>> getAllClinics(@RequestHeader("Authorization") String authHeader) {
        try{
            return this.ok(this.clinicService.getAllClinics(authHeader));
        } catch (UnauthorizedException e) {
            return this.error("Nuk jeni i autorizuar", HttpStatus.UNAUTHORIZED);
        }
    }

    /**
     * Kthen kliniken me ID-ne e specifikuar
     *
     * @param authHeader tokeni i autentikimit
     * @param clinicId   ID e klinikes
     * @return klinika e kerkuar
     */
    @GetMapping("/{clinicId}")
    public ResponseEntity<ApiResponse<Clinic>> getClinicById(@RequestHeader("Authorization") String authHeader, @PathVariable("clinicId") Long clinicId) {
        try{
            Clinic clinic = this.clinicService.getClinicById(clinicId, authHeader);
            return this.ok(clinic);
        } catch (JWTVerificationException | UnauthorizedException e) {
            return this.error("Nuk jeni i autorizuar", HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            return this.error("Klinika nuk ekziston", HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Krijon nje klinike te re
     *
     * @param authHeader tokeni i autentikimit
     * @param clinic     detajet e klinikes
     * @return mesazhi i suksesit
     */
    @PostMapping
    public ResponseEntity<ApiResponse<String>> createClinic(@RequestHeader("Authorization") String authHeader, @RequestBody Clinic clinic) {
        try {
            clinicService.saveClinic(clinic, authHeader);
            return this.ok( "Klinika u ruajt me sukses");
        } catch (JWTVerificationException e) {
            return this.error("Nuk jeni i autorizuar", HttpStatus.UNAUTHORIZED);
        }
    }

    /**
     * Perditeson kliniken ekzistuese
     *
     * @param authHeader tokeni i autentikimit
     * @param clinicId   ID e klinikes
     * @param clinic     te dhenat e reja te klinikes
     * @return mesazhi i suksesit
     */
    @PutMapping("/{clinicId}")
    public ResponseEntity<ApiResponse<String>> updateClinic(@RequestHeader("Authorization") String authHeader, @PathVariable("clinicId") Long clinicId, @RequestBody Clinic clinic) {
        try {
            this.clinicService.updateClinic(clinicId, clinic, authHeader);
            return this.ok("Klinika u perditsua me sukses");
        } catch (JWTVerificationException | UnauthorizedException e) {
            return this.error("Nuk jeni i autorizuar", HttpStatus.UNAUTHORIZED);
        } catch (NotFoundException e) {
            return this.error("Klinika nuk eksizton", HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Fshin kliniken me ID-ne e specifikuar
     *
     * @param authHeader tokeni i autentikimit
     * @param clinicId   ID e klinikes
     * @return mesazhi i suksesit
     */
    @DeleteMapping("/{clinicId}")
    public ResponseEntity<ApiResponse<String>> deleteClinic(@RequestHeader("Authorization") String authHeader, @PathVariable("clinicId") Long clinicId) {
        try {
            this.clinicService.deleteClinic(clinicId, authHeader);
            return this.ok("Klinika u fshi me sukses");
        } catch (JWTVerificationException | UnauthorizedException e) {
            return this.error("Nuk jeni i autorizuar", HttpStatus.UNAUTHORIZED);
        } catch (NotFoundException e) {
            return this.error("Klinika nuk eksizton", HttpStatus.NOT_FOUND);
        }
    }
}