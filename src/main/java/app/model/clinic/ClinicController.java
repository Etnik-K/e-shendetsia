package app.model.clinic;

import app.exception.NotFoundException;
import app.exception.UnauthorizedException;
import app.util.ApiResponse;
import app.util.BaseController;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clinics")
public class ClinicController extends BaseController {

    private final ClinicService clinicService;

    @Autowired
    public ClinicController(ClinicServiceImplementation clinicService) {
        this.clinicService = clinicService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Clinic>>> getAllClinics(@RequestHeader("Authorization") String authHeader) {
        try{
            return this.ok(this.clinicService.getAllClinics(authHeader));
        } catch (UnauthorizedException e) {
            return this.error("Nuk jeni i autorizuar", HttpStatus.UNAUTHORIZED);
        }
    }

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

    @PostMapping
    public ResponseEntity<ApiResponse<String>> createClinic(@RequestHeader("Authorization") String authHeader, @RequestBody Clinic clinic) {
        try {
            clinicService.saveClinic(clinic, authHeader);
            return this.ok( "Klinika u ruajt me sukses");
        } catch (JWTVerificationException e) {
            return this.error("Nuk jeni i autorizuar", HttpStatus.UNAUTHORIZED);
        }
    }

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