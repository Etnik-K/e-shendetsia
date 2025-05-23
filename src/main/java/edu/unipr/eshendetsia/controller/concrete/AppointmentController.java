package edu.unipr.eshendetsia.controller.concrete;
import edu.unipr.eshendetsia.controller.BaseController;
import edu.unipr.eshendetsia.exception.concrete.UnauthorizedException;
import edu.unipr.eshendetsia.http.request.body.CreateAppointmentRequest;
import edu.unipr.eshendetsia.service.interfaces.AppointmentService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Kontrollues qe menaxhon te gjitha kerkesat e lidhura me terminet
 */
@AllArgsConstructor
@RestController
@RequestMapping("/appointments")
public class AppointmentController extends BaseController {

    private final AppointmentService appointmentService;

    /**
     * Krijon nje termin te ri
     *
     * @param appointmentRequest termini qe do te krijohet
     * @return terminin e krijuar
     */
    @PostMapping("/appointments")
    public ResponseEntity<String> create(@RequestBody CreateAppointmentRequest appointmentRequest, @RequestHeader("Authorization") String authHeader) throws UnauthorizedException {
        appointmentService.save(appointmentRequest.toAppointment(), authHeader);
        return this.ok("Termini u caktua me sukses");
    }

    /**
     * Anulon nje termin ekzistues
     *
     * @param id identifikuesi i terminit
     * @return pergjigje bosh me status 204
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> cancel(@PathVariable Long id, @RequestHeader("Authorization") String authHeader) throws UnauthorizedException {
        this.appointmentService.cancel(id, authHeader);
        return this.ok("Termini eshte anuluar me sukses");
    }
}
