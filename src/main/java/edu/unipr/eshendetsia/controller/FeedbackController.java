package edu.unipr.eshendetsia.controller;

import edu.unipr.eshendetsia.model.entity.Feedback;
import edu.unipr.eshendetsia.repository.FeedbackRepository;
import edu.unipr.eshendetsia.service.implementation.FeedbackServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/feedback")
public class FeedbackController {

    private final FeedbackServiceImplementation feedbackServiceImplementation;

    @Autowired
    public FeedbackController(FeedbackServiceImplementation feedbackServiceImplementation) {
        this.feedbackServiceImplementation = feedbackServiceImplementation;
    }

    @PostMapping
    public ResponseEntity<Feedback> submit(@RequestBody Feedback feedback) {
        return ResponseEntity.ok(feedbackServiceImplementation.save(feedback));
    }

    @GetMapping("/doctor/{doctorId}")
    public ResponseEntity<List<Feedback>> getByDoctor(@PathVariable Long doctorId) {
        return ResponseEntity.ok(feedbackServiceImplementation.getByDoctorId(doctorId));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Feedback>> getByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(feedbackServiceImplementation.getByUserId(userId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        feedbackServiceImplementation.delete(id);
        return ResponseEntity.noContent().build();
    }

}
