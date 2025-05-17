package edu.unipr.eshendetsia.controller;

import edu.unipr.eshendetsia.model.entity.Appointment;
import edu.unipr.eshendetsia.repository.AppointmentRepository;
import edu.unipr.eshendetsia.service.implementation.AppointmentServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {
    private final AppointmentServiceImplementation appointmentServiceImplementation;

    @Autowired
    public AppointmentController(AppointmentServiceImplementation appointmentServiceImplementation) {
        this.appointmentServiceImplementation = appointmentServiceImplementation;
    }

    @PostMapping
    public ResponseEntity<Appointment> create(@RequestBody Appointment appointment) {
        return ResponseEntity.ok(appointmentServiceImplementation.save(appointment));
    }

    @PutMapping("/{id}/cancel")
    public ResponseEntity<Void> cancel(@PathVariable Long id) {
        appointmentServiceImplementation.cancel(id);
        return ResponseEntity.noContent().build();
    }
}
