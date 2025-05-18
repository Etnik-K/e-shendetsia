package edu.unipr.eshendetsia.controller;

import edu.unipr.eshendetsia.model.entity.Allergy;
import edu.unipr.eshendetsia.service.implementation.AllergyServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/allergies")
public class AllergyController {

    private final AllergyServiceImplementation allergyService;

    @Autowired
    public AllergyController(AllergyServiceImplementation allergyService) {
        this.allergyService = allergyService;
    }

    @PostMapping
    public ResponseEntity<Allergy> create(@RequestBody Allergy allergy) {
        return ResponseEntity.ok(allergyService.save(allergy));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Allergy>> getByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(allergyService.getByUserId(userId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        allergyService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
