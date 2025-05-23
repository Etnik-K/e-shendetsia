package edu.unipr.eshendetsia.controller.concrete;

import edu.unipr.eshendetsia.exception.concrete.NotFoundException;
import edu.unipr.eshendetsia.exception.concrete.UnauthorizedException;
import edu.unipr.eshendetsia.model.entity.Clinic;
import edu.unipr.eshendetsia.service.interfaces.ClinicService;
import edu.unipr.eshendetsia.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<List<Clinic>> getAllClinics(@RequestHeader("Authorization") String authHeader) throws UnauthorizedException {
            return this.ok(this.clinicService.getAllClinics(authHeader));
    }

    /**
     * Kthen kliniken me ID-ne e specifikuar
     *
     * @param authHeader tokeni i autentikimit
     * @param clinicId   ID e klinikes
     * @return klinika e kerkuar
     */
    @GetMapping("/{clinicId}")
    public ResponseEntity<Clinic> getClinicById(@RequestHeader("Authorization") String authHeader, @PathVariable("clinicId") Long clinicId) throws UnauthorizedException {
            Clinic clinic = this.clinicService.getClinicById(clinicId, authHeader);
            return this.ok(clinic);
    }

    /**
     * Krijon nje klinike te re
     *
     * @param authHeader tokeni i autentikimit
     * @param clinic     detajet e klinikes
     * @return mesazhi i suksesit
     */
    @PostMapping
    public ResponseEntity<String> createClinic(@RequestHeader("Authorization") String authHeader, @RequestBody Clinic clinic) {
            clinicService.saveClinic(clinic, authHeader);
            return this.ok( "Klinika u ruajt me sukses");
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
    public ResponseEntity<String> updateClinic(
            @RequestHeader("Authorization") String authHeader,
            @PathVariable("clinicId") Long clinicId,
            @RequestBody Clinic clinic)
                throws UnauthorizedException, NotFoundException {

            this.clinicService.updateClinic(clinicId, clinic, authHeader);
            return this.ok("Klinika u perditsua me sukses");
    }

    /**
     * Fshin kliniken me ID-ne e specifikuar
     *
     * @param authHeader tokeni i autentikimit
     * @param clinicId   ID e klinikes
     * @return mesazhi i suksesit
     */
    @DeleteMapping("/{clinicId}")
    public ResponseEntity<String> deleteClinic(
            @RequestHeader("Authorization") String authHeader,
            @PathVariable("clinicId") Long clinicId)
                throws UnauthorizedException, NotFoundException {
            this.clinicService.deleteClinic(clinicId, authHeader);
            return this.ok("Klinika u fshi me sukses");
    }
}