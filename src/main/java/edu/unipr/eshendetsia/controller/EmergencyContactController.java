package edu.unipr.eshendetsia.controller;

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
public class EmergencyContactController {

    private final EmergencyContactService emergencyContactService;

    public EmergencyContactController(EmergencyContactService emergencyContactService) {
        this.emergencyContactService = emergencyContactService;
    }

    /**
     * Krijon nje kontakt te ri emergjent
     *
     * @param contact kontakti emergjent qe do te krijohet
     * @return kontaktin e krijuar emergjent
     */
    @PostMapping
    public ResponseEntity<EmergencyContact> create(@RequestBody EmergencyContact contact) {
        return ResponseEntity.ok(emergencyContactService.save(contact));
    }

    /**
     * Merr listen e kontakteve emergjente per nje perdorues
     *
     * @param userId ID e perdoruesit
     * @return listen e kontakteve emergjente
     */
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<EmergencyContact>> getByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(emergencyContactService.getByUserId(userId));
    }

    /**
     * Fshin nje kontakt emergjent
     *
     * @param id ID e kontaktit emergjent
     * @return pergjigje bosh me status 204
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        emergencyContactService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
