package edu.unipr.eshendetsia.controller.concrete;

import edu.unipr.eshendetsia.controller.BaseController;
import edu.unipr.eshendetsia.http.request.body.SubmitFeedbackRequest;
import edu.unipr.eshendetsia.model.entity.Feedback;
import edu.unipr.eshendetsia.service.interfaces.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Kontrolleri per menaxhimin e feedack-ut te pacienteve.
 * Mundeson ruajtjen, marrjen dhe fshirjen e feedback-ut.
 */
@RestController
@RequestMapping("/feedback")
public class FeedbackController extends BaseController {

    private final FeedbackService feedbackService;

    @Autowired
    public FeedbackController(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    /**
     * Ruan nje feedback te ri nga pacienti
     *
     * @param feedbackRequest Fedback-u qe do ruhet
     * @return feedback-u i ruajtur
     */
    @PostMapping
    public ResponseEntity<String> submit(@RequestBody SubmitFeedbackRequest feedbackRequest) {
        feedbackService.save(feedbackRequest.toFeedback());
        return this.ok("Feedback-u u ruajt me sukses");
    }

    /**
     * Merr te gjitha feedback-et per nje doktor specifik
     *
     * @param doctorId ID e doktorit
     * @return lista e feedback-eve per doktorin
     */
    @GetMapping("/doctor/{doctorId}")
    public ResponseEntity<List<Feedback>> getByDoctor(@PathVariable Long doctorId, @RequestHeader("Authorization") String authHeader) {
        return this.ok(feedbackService.getByDoctorId(doctorId, authHeader));
    }

    /**
     * Merr te gjitha feedback-et nga nje perdorues specifik
     *
     * @param userId ID e perdoruesit
     * @return lista e feedback-eve nga perdoruesi
     */
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Feedback>> getByUser(@PathVariable Long userId, @RequestHeader("Authorization") String authHeader) {
        return this.ok(feedbackService.getByUserId(userId, authHeader));
    }

    /**
     * Fshin nje feedback specifik
     *
     * @param id ID e feedback-ut per tu fshire
     * @return pergjigje pa permbajtje
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id, @RequestHeader("Authorization") String authHeader) {
        this.feedbackService.delete(id, authHeader);
        return this.ok("Feedback-u u fshi me sukses");
    }

}
