package edu.unipr.eshendetsia.controller;

import edu.unipr.eshendetsia.model.entity.Appointment;
import edu.unipr.eshendetsia.repository.AppointmentRepository;
import edu.unipr.eshendetsia.service.implementation.AppointmentServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Kontrollues qe menaxhon te gjitha kerkesat e lidhura me terminet
 */
@RestController
@RequestMapping("/appointments")
public class AppointmentController {
    private final AppointmentServiceImplementation appointmentServiceImplementation;

    /**
     * Konstruktor qe inicializon kontrolluesen e termineve
     *
     * @param appointmentServiceImplementation implementimi i sherbimit te termineve
     */
    @Autowired
    public AppointmentController(AppointmentServiceImplementation appointmentServiceImplementation) {
        this.appointmentServiceImplementation = appointmentServiceImplementation;
    }

    /**
     * Krijon nje termin te ri
     *
     * @param appointment termini qe do te krijohet
     * @return terminin e krijuar
     */
    @PostMapping
    public ResponseEntity<Appointment> create(@RequestBody Appointment appointment) {
        return ResponseEntity.ok(appointmentServiceImplementation.save(appointment));
    }

    /**
     * Anulon nje termin ekzistues
     *
     * @param id identifikuesi i terminit
     * @return pergjigje bosh me status 204
     */
    @PutMapping("/{id}/cancel")
    public ResponseEntity<Void> cancel(@PathVariable Long id) {
        appointmentServiceImplementation.cancel(id);
        return ResponseEntity.noContent().build();
    }
}
