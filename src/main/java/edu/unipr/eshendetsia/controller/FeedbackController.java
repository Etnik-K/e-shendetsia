package edu.unipr.eshendetsia.controller;

import edu.unipr.eshendetsia.model.entity.Feedback;
import edu.unipr.eshendetsia.repository.FeedbackRepository;
import edu.unipr.eshendetsia.service.implementation.FeedbackServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Kontrolleri per menaxhimin e mendimeve te pacienteve.
 * Mundeson ruajtjen, marrjen dhe fshirjen e feedback-ut.
 */
@RestController
@RequestMapping("/feedback")
public class FeedbackController {

    private final FeedbackServiceImplementation feedbackServiceImplementation;

    @Autowired
    public FeedbackController(FeedbackServiceImplementation feedbackServiceImplementation) {
        this.feedbackServiceImplementation = feedbackServiceImplementation;
    }

    /**
     * Ruan nje mendim te ri nga pacienti
     *
     * @param feedback mendimi qe do ruhet
     * @return mendimi i ruajtur
     */
    @PostMapping
    public ResponseEntity<Feedback> submit(@RequestBody Feedback feedback) {
        return ResponseEntity.ok(feedbackServiceImplementation.save(feedback));
    }

    /**
     * Merr te gjitha mendimet per nje doktor specifik
     *
     * @param doctorId ID e doktorit
     * @return lista e mendimeve per doktorin
     */
    @GetMapping("/doctor/{doctorId}")
    public ResponseEntity<List<Feedback>> getByDoctor(@PathVariable Long doctorId) {
        return ResponseEntity.ok(feedbackServiceImplementation.getByDoctorId(doctorId));
    }

    /**
     * Merr te gjitha mendimet nga nje perdorues specifik
     *
     * @param userId ID e perdoruesit
     * @return lista e mendimeve nga perdoruesi
     */
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Feedback>> getByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(feedbackServiceImplementation.getByUserId(userId));
    }

    /**
     * Fshin nje mendim specifik
     *
     * @param id ID e mendimit per tu fshire
     * @return pergjigje pa permbajtje
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        feedbackServiceImplementation.delete(id);
        return ResponseEntity.noContent().build();
    }

}
