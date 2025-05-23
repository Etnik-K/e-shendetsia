package edu.unipr.eshendetsia.controller.concrete;
import edu.unipr.eshendetsia.controller.BaseController;
import edu.unipr.eshendetsia.exception.concrete.UnauthorizedException;
import edu.unipr.eshendetsia.http.request.body.CreateAppointmentRequest;
import edu.unipr.eshendetsia.service.interfaces.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<String> create(@RequestBody CreateAppointmentRequest appointmentRequest) throws UnauthorizedException {
        appointmentService.save(appointmentRequest.toAppointment());
        return this.ok("Termini u caktua me sukses");
    }

    /**
     * Anulon nje termin ekzistues
     *
     * @param id identifikuesi i terminit
     * @return pergjigje bosh me status 204
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> cancel(@PathVariable Long id) throws UnauthorizedException {
        this.appointmentService.cancel(id);
        return this.ok("Termini eshte anuluar me sukses");
    }
}
