package edu.unipr.eshendetsia.controller;

import com.auth0.jwt.exceptions.JWTVerificationException;
import edu.unipr.eshendetsia.controller.base.BaseController;
import edu.unipr.eshendetsia.exception.NotFoundException;
import edu.unipr.eshendetsia.exception.UnauthorizedException;
import edu.unipr.eshendetsia.http.response.ApiResponse;
import edu.unipr.eshendetsia.model.entity.Notification;
import edu.unipr.eshendetsia.service.interfaces.NotificationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// qita spe preki, spe di qysh e ka njet me funksionu - zhushi

/**
 * Kontrolluesi i njoftimeve menaxhon te gjitha kerkesat e lidhura me njoftimet.
 * Mundeson dergimin, marrjen, shenimin si te lexuar dhe fshirjen e njoftimeve.
 */
@RestController
@RequestMapping("/notifications")
public class NotificationController extends BaseController {
    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Notification>> send(@RequestBody Notification notification) {
        return this.ok(notificationService.save(notification));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<ApiResponse<List<Notification>>> getByUser(@PathVariable Long userId) {
        return this.ok(notificationService.getByUser(userId));
    }

    @PutMapping("/{id}/read")
    public ResponseEntity<ApiResponse<String>> markAsRead(@PathVariable Long id) {
        try{
            this.notificationService.markAsRead(id);
            return this.ok("Eshte vendosur njoftimi");
        } catch (JWTVerificationException | UnauthorizedException exception) {
            return this.error("Nuk jeni i autorizuar", HttpStatus.UNAUTHORIZED);
        }catch (NotFoundException exception){
            return this.error("Nuk eshte gjetur",HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> delete(@PathVariable Long id) {
        try{
            this.notificationService.delete(id);
            return this.ok("Notification u fshi me sukses");
        } catch (JWTVerificationException | UnauthorizedException exception) {
            return this.error("Nuk jeni i autorizuar", HttpStatus.UNAUTHORIZED);
        }
    }
}
