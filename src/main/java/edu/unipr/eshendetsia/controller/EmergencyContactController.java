package edu.unipr.eshendetsia.controller;

import edu.unipr.eshendetsia.model.entity.EmergencyContact;
import edu.unipr.eshendetsia.service.implementation.EmergencyContactServiceImplementation;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Getter
@Setter
@RestController
@RequestMapping("/emergency_contacts")
public class EmergencyContactController {

    private final EmergencyContactServiceImplementation service;

    public EmergencyContactController(EmergencyContactServiceImplementation service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<EmergencyContact> create(@RequestBody EmergencyContact contact) {
        return ResponseEntity.ok(service.save(contact));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<EmergencyContact>> getByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(service.getByUserId(userId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
