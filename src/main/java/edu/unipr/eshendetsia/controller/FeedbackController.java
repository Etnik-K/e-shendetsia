package edu.unipr.eshendetsia.controller;

import com.auth0.jwt.exceptions.JWTVerificationException;
import edu.unipr.eshendetsia.controller.base.BaseController;
import edu.unipr.eshendetsia.exception.NotFoundException;
import edu.unipr.eshendetsia.exception.UnauthorizedException;
import edu.unipr.eshendetsia.http.request.body.SubmitFeedbackRequest;
import edu.unipr.eshendetsia.http.response.ApiResponse;
import edu.unipr.eshendetsia.model.entity.Feedback;
import edu.unipr.eshendetsia.service.interfaces.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<ApiResponse<String>> submit(@RequestBody SubmitFeedbackRequest feedbackRequest) {
        try{
            feedbackService.save(feedbackRequest.toFeedback());
            return this.ok("Feedback-u u ruajt me sukses");
        } catch (UnauthorizedException | JWTVerificationException e) {
            return this.error("Nuk jeni i autorizuar", HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Merr te gjitha feedback-et per nje doktor specifik
     *
     * @param doctorId ID e doktorit
     * @return lista e feedback-eve per doktorin
     */
    @GetMapping("/doctor/{doctorId}")
    public ResponseEntity<ApiResponse<List<Feedback>>> getByDoctor(@PathVariable Long doctorId) {
        try{
            return this.ok(feedbackService.getByDoctorId(doctorId));
        } catch (UnauthorizedException | JWTVerificationException e) {
            return this.error("Nuk jeni i autorizuar", HttpStatus.UNAUTHORIZED);
        } catch (NotFoundException e) {
            return this.error("Doktori nuk eshte gjetur", HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Merr te gjitha feedback-et nga nje perdorues specifik
     *
     * @param userId ID e perdoruesit
     * @return lista e feedback-eve nga perdoruesi
     */
    @GetMapping("/user/{userId}")
    public ResponseEntity<ApiResponse<List<Feedback>>> getByUser(@PathVariable Long userId) {
        try{
            return this.ok(feedbackService.getByUserId(userId));
        } catch (UnauthorizedException | JWTVerificationException e) {
            return this.error("Nuk jeni i autorizuar", HttpStatus.UNAUTHORIZED);
        } catch (NotFoundException e) {
            return this.error("Useri nuk eshte gjetur", HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Fshin nje feedback specifik
     *
     * @param id ID e feedback-ut per tu fshire
     * @return pergjigje pa permbajtje
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> delete(@PathVariable Long id) {
        try{
            this.feedbackService.delete(id);
            return this.ok("Feedback-u u fshi me sukses");
        } catch (JWTVerificationException | UnauthorizedException exception) {
            return this.error("Nuk jeni i autorizuar", HttpStatus.UNAUTHORIZED);
        }
    }

}
