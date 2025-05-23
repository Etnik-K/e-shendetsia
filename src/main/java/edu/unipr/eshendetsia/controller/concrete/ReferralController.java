package edu.unipr.eshendetsia.controller.concrete;

import com.auth0.jwt.exceptions.JWTVerificationException;
import edu.unipr.eshendetsia.controller.BaseController;
import edu.unipr.eshendetsia.exception.concrete.NotFoundException;
import edu.unipr.eshendetsia.exception.concrete.UnauthorizedException;
import edu.unipr.eshendetsia.http.request.body.CreateReferralRequest;
import edu.unipr.eshendetsia.http.response.ApiResponse;
import edu.unipr.eshendetsia.model.entity.Referral;
import edu.unipr.eshendetsia.service.interfaces.ReferralService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Kontrolluesi per menaxhimin e referimeve mjekesore.
 * Ofron endpoint-e per krijimin, leximin dhe fshirjen e referimeve.
 */
@Setter
@Getter
@RestController
@RequestMapping("/referrals")
public class ReferralController extends BaseController {
    private final ReferralService referralService;

    @Autowired
    public ReferralController(ReferralService referralService) {
            this.referralService = referralService;
        }

    /**
     * Krijon nje referim te ri mjekesor.
     *
     * @param referralRequest Referimi qe do te krijohet
     * @return Referimi i krijuar
     */
    @PostMapping
    public ResponseEntity<String> create(@RequestBody CreateReferralRequest referralRequest) {
        referralService.save(referralRequest.toReferral());
        return this.ok("Referimi u krijua me sukses");
    }

    /**
     * Merr te gjitha referimet e nje pacienti.
     *
     * @param patientId ID e pacientit
     * @return Lista e referimeve
     */
    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<Referral>> getByPatient(@PathVariable Long patientId) {
        return this.ok(referralService.getByPatient(patientId));
    }

    /**
     * Merr te gjitha referimet per nje doktor.
     *
     * @param doctorId ID e doktorit
     * @return Lista e referimeve
     */
    @GetMapping("/doctor/{doctorId}")
    public ResponseEntity<List<Referral>> getByDoctor(@PathVariable Long doctorId) {
        return this.ok(referralService.getByReceivingDoctor(doctorId));
    }

    /**
     * Fshin nje referim.
     *
     * @param id ID e referimit per tu fshire
     * @return Pergjigje pa permbajtje
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        this.referralService.delete(id);
        return this.ok("Referimi u fshi me sukses");
    }
}
