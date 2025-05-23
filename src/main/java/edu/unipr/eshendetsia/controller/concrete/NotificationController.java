package edu.unipr.eshendetsia.controller.concrete;

import edu.unipr.eshendetsia.controller.BaseController;
import edu.unipr.eshendetsia.model.entity.Notification;
import edu.unipr.eshendetsia.service.interfaces.NotificationService;
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
    public ResponseEntity<String> send(@RequestBody Notification notification) {
        notificationService.save(notification);
        return this.ok("U ruajt me sukses");
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Notification>> getByUser(@PathVariable Long userId, @RequestHeader("Authorization") String authHeader) {
        return this.ok(notificationService.getByUser(userId, authHeader));
    }

    @PutMapping("/{id}/read")
    public ResponseEntity<String> markAsRead(@PathVariable Long id) {
        this.notificationService.markAsRead(id);
        return this.ok("Eshte vendosur njoftimi");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id, @RequestHeader("Authorization") String authHeader) {
        this.notificationService.delete(id, authHeader);
        return this.ok("Notification u fshi me sukses");
    }
}
