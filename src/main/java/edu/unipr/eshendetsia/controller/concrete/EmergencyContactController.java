package edu.unipr.eshendetsia.controller.concrete;


import edu.unipr.eshendetsia.controller.BaseController;
import edu.unipr.eshendetsia.http.request.body.CreateEmergencyContactRequest;
import edu.unipr.eshendetsia.model.entity.EmergencyContact;
import edu.unipr.eshendetsia.service.interfaces.EmergencyContactService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Kontrollues per menaxhimin e kontakteve emergjente.
 * Mundeson krijimin, marrjen dhe fshirjen e kontakteve emergjente.
 * Komunikon me sherbimin EmergencyContactService per te realizuar operacionet.
 */
@Getter
@Setter
@RestController
@RequestMapping("/emergency_contacts")
public class EmergencyContactController extends BaseController {

    private final EmergencyContactService emergencyContactService;

    public EmergencyContactController(EmergencyContactService emergencyContactService) {
        this.emergencyContactService = emergencyContactService;
    }

    /**
     * Krijon nje kontakt te ri emergjent
     *
     * @param emergyContactRequest kontakti emergjent qe do te krijohet
     * @return kontaktin e krijuar emergjent
     */
    @PostMapping
    public ResponseEntity<String> create(@RequestBody CreateEmergencyContactRequest emergyContactRequest) {
        emergencyContactService.save(emergyContactRequest.toEmergencyContact());
        return this.ok("Kontakti u krijua me sukses");
    }

    /**
     * Merr listen e kontakteve emergjente per nje perdorues
     *
     * @param userId ID e perdoruesit
     * @return listen e kontakteve emergjente
     */
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<EmergencyContact>> getByUser(@PathVariable Long userId, @RequestHeader("Authorization") String requestJwt) {
        return this.ok(emergencyContactService.getByUserId(userId, requestJwt));
    }

    /**
     * Fshin nje kontakt emergjent
     *
     * @param id ID e kontaktit emergjent
     * @return pergjigje bosh me status 204
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id, @RequestHeader("Authorization") String requestJwt, Long userId) {
        this.emergencyContactService.delete(id, requestJwt, userId);
        return this.ok("Kontakti u fshi me sukses");
    }
}
