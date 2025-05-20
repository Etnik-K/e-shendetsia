package edu.unipr.eshendetsia.controller;

import com.auth0.jwt.exceptions.JWTVerificationException;
import edu.unipr.eshendetsia.controller.base.BaseController;
import edu.unipr.eshendetsia.exception.InvalidCredentialsException;
import edu.unipr.eshendetsia.exception.NotFoundException;
import edu.unipr.eshendetsia.exception.UnauthorizedException;
import edu.unipr.eshendetsia.http.response.ApiResponse;
import edu.unipr.eshendetsia.model.entity.Feedback;
import edu.unipr.eshendetsia.service.interfaces.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Kontrolleri per menaxhimin e mendimeve te pacienteve.
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
     * Ruan nje mendim te ri nga pacienti
     *
     * @param feedback mendimi qe do ruhet
     * @return mendimi i ruajtur
     */
    @PostMapping
    public ResponseEntity<ApiResponse<Feedback>> submit(@RequestBody Feedback feedback) {
                    return this.ok(feedbackService.save(feedback));
    }

    /**
     * Merr te gjitha mendimet per nje doktor specifik
     *
     * @param doctorId ID e doktorit
     * @return lista e mendimeve per doktorin
     */
    @GetMapping("/doctor/{doctorId}")
    public ResponseEntity<ApiResponse<List<Feedback>>> getByDoctor(@PathVariable Long doctorId) {
        return this.ok(feedbackService.getByDoctorId(doctorId));
    }

    /**
     * Merr te gjitha mendimet nga nje perdorues specifik
     *
     * @param userId ID e perdoruesit
     * @return lista e mendimeve nga perdoruesi
     */
    @GetMapping("/user/{userId}")
    public ResponseEntity<ApiResponse<List<Feedback>>> getByUser(@PathVariable Long userId) {
        return this.ok(feedbackService.getByUserId(userId));
    }

    /**
     * Fshin nje mendim specifik
     *
     * @param id ID e mendimit per tu fshire
     * @return pergjigje pa permbajtje
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> delete(@PathVariable Long id) {
        try{
            this.feedbackService.delete(id);
            return this.ok("Mendimi u fshi me sukses");
        } catch (JWTVerificationException | UnauthorizedException exception) {
            return this.error("Nuk jeni i autorizuar", HttpStatus.UNAUTHORIZED);
        }
    }

}
