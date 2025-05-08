package app.model.clinic;

import app.util.ApiResponse;
import app.util.BaseController;
import app.util.JWTUtil;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clinics")
public class ClinicController extends BaseController {

    private final ClinicService clinicService;

    @Autowired
    public ClinicController(ClinicService clinicService) {
        this.clinicService = clinicService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Clinic>>> getAllClinics(@RequestHeader("Authorization") String authHeader) {
        verifyToken(authHeader);
        List<Clinic> clinics = clinicService.getAllClinics();
        return ResponseEntity.ok(new ApiResponse<>(true, clinics, null));
    }

    @GetMapping("/{clinicId}")
    public ResponseEntity<ApiResponse<Clinic>> getClinicById(@RequestHeader("Authorization") String authHeader, @PathVariable("clinicId") Long clinicId) {
        verifyToken(authHeader);
        if (clinicId <= 0) {
            return ResponseEntity.badRequest().body(new ApiResponse<>(false, null, "Invalid clinic ID"));
        }
        Optional<Clinic> clinic = clinicService.getClinicById(clinicId);
        return clinic.map(c -> ResponseEntity.ok(new ApiResponse<>(true, c, null)))
                .orElseGet(() -> ResponseEntity.status(404).body(new ApiResponse<>(false, null, STR."Klinika me ID \{clinicId} nuk eksiston")));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Clinic>> createClinic(@RequestHeader("Authorization") String authHeader, @RequestBody Clinic clinic) {
        DecodedJWT jwt = verifyToken(authHeader);
        if (!isAdminOrDirector(jwt)) {
            return ResponseEntity.status(403).body(new ApiResponse<>(false, null, "Unauthorized: Admin or Director role required"));
        }
        Clinic savedClinic = clinicService.saveClinic(clinic);
        return ResponseEntity.ok(new ApiResponse<>(true, savedClinic, null));
    }

    @PutMapping("/{clinicId}")
    public ResponseEntity<ApiResponse<Clinic>> updateClinic(@RequestHeader("Authorization") String authHeader, @PathVariable("clinicId") Long clinicId, @RequestBody Clinic clinic) {
        DecodedJWT jwt = verifyToken(authHeader);
        if (!isAdminOrDirector(jwt)) {
            return ResponseEntity.status(403).body(new ApiResponse<>(false, null, "Unauthorized: Admin or Director role required"));
        }
        if (clinicId <= 0) {
            return ResponseEntity.badRequest().body(new ApiResponse<>(false, null, "Invalid clinic ID"));
        }
        Optional<Clinic> updatedClinic = clinicService.updateClinic(clinicId, clinic);
        return updatedClinic.map(c -> ResponseEntity.ok(new ApiResponse<>(true, c, null)))
                .orElseGet(() -> ResponseEntity.status(404).body(new ApiResponse<>(false, null, STR."Klinika me ID \{clinicId} nuk eksiston")));
    }

    @DeleteMapping("/{clinicId}")
    public ResponseEntity<ApiResponse<Void>> deleteClinic(@RequestHeader("Authorization") String authHeader, @PathVariable("clinicId") Long clinicId) {
        DecodedJWT jwt = verifyToken(authHeader);
        if (!isAdminOrDirector(jwt)) {
            return ResponseEntity.status(403).body(new ApiResponse<>(false, null, "Unauthorized: Admin or Director role required"));
        }
        if (clinicId <= 0) {
            return ResponseEntity.badRequest().body(new ApiResponse<>(false, null, "Invalid clinic ID"));
        }
        boolean deleted = clinicService.deleteClinic(clinicId);
        if (deleted) {
            return ResponseEntity.ok(new ApiResponse<>(true, null, null));
        }
        return ResponseEntity.status(404).body(new ApiResponse<>(false, null, STR."Klinika me ID \{clinicId} nuk eksiston"));
    }

    private DecodedJWT verifyToken(String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new JWTVerificationException("Invalid Authorization header");
        }
        String token = authHeader.replace("Bearer ", "");
        return JWTUtil.verifyToken(token);
    }

    private boolean isAdminOrDirector(DecodedJWT jwt) {
        String role = jwt.getClaim("role").asString();
        return "ADMIN".equals(role) || "DIRECTOR".equals(role);
    }
}