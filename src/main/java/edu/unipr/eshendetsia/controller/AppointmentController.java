package edu.unipr.eshendetsia.controller;

import com.auth0.jwt.exceptions.JWTVerificationException;
import edu.unipr.eshendetsia.controller.abstracts.BaseController;
import edu.unipr.eshendetsia.exception.UnauthorizedException;
import edu.unipr.eshendetsia.http.request.body.CreateAppointmentRequest;
import edu.unipr.eshendetsia.http.response.ApiResponse;
import edu.unipr.eshendetsia.service.interfaces.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Kontrollues qe menaxhon te gjitha kerkesat e lidhura me terminet
 */
@RestController
@RequestMapping("/appointments")
public class AppointmentController extends BaseController {
    private final AppointmentService appointmentService;

    /**
     * Konstruktor qe inicializon kontrolluesen e termineve
     *
     * @param appointmentService implementimi i sherbimit te termineve
     */
    @Autowired
    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    /**
     * Krijon nje termin te ri
     *
     * @param appointmentRequest termini qe do te krijohet
     * @return terminin e krijuar
     */
    @PostMapping("/appointments")
    public ResponseEntity<ApiResponse<String>> create(@RequestBody CreateAppointmentRequest appointmentRequest) {
        try{
            appointmentService.save(appointmentRequest.toAppointment());
            return this.ok("Termini u caktua me sukses");
        } catch (UnauthorizedException | JWTVerificationException e) {
            return this.error("Nuk jeni i autorizuar", HttpStatus.UNAUTHORIZED);
        }
    }

    /**
     * Anulon nje termin ekzistues
     *
     * @param id identifikuesi i terminit
     * @return pergjigje bosh me status 204
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> cancel(@PathVariable Long id) {
        try {
            this.appointmentService.cancel(id);
            return this.ok("Termini eshte anuluar me sukses");
        } catch (JWTVerificationException | UnauthorizedException exception) {
            return this.error("Nuk jeni i autorizuar", HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            return this.error("Termini nuk eshte anuluar", HttpStatus.NOT_FOUND);
        }
    }
}
