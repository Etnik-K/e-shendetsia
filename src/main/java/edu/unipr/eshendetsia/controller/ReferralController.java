package edu.unipr.eshendetsia.controller;

import edu.unipr.eshendetsia.model.entity.Referral;
import edu.unipr.eshendetsia.service.interfaces.ReferralService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
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
public class ReferralController {
        private final ReferralService service;

        @Autowired
        public ReferralController(ReferralService service) {
            this.service = service;
        }

    /**
     * Krijon nje referim te ri mjekesor.
     *
     * @param referral Referimi qe do te krijohet
     * @return Referimi i krijuar
     */
    @PostMapping
    public ResponseEntity<Referral> create(@RequestBody Referral referral) {
        return ResponseEntity.ok(service.save(referral));
    }

    /**
     * Merr te gjitha referimet e nje pacienti.
     *
     * @param patientId ID e pacientit
     * @return Lista e referimeve
     */
    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<Referral>> getByPatient(@PathVariable Long patientId) {
        return ResponseEntity.ok(service.getByPatient(patientId));
    }

    /**
     * Merr te gjitha referimet per nje doktor.
     *
     * @param doctorId ID e doktorit
     * @return Lista e referimeve
     */
    @GetMapping("/doctor/{doctorId}")
    public ResponseEntity<List<Referral>> getByDoctor(@PathVariable Long doctorId) {
        return ResponseEntity.ok(service.getByReceivingDoctor(doctorId));
    }

    /**
     * Fshin nje referim.
     *
     * @param id ID e referimit per tu fshire
     * @return Pergjigje pa permbajtje
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
