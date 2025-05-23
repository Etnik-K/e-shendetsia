package edu.unipr.eshendetsia.controller.concrete;

import edu.unipr.eshendetsia.http.request.body.LoginRequest;
import edu.unipr.eshendetsia.model.entity.User;
import edu.unipr.eshendetsia.service.interfaces.UserService;
import edu.unipr.eshendetsia.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController extends BaseController{

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Kjo Metode eshte endpoint e cila kthen te gjithe userat ne qofte se useri qe e invokon eshte admin,
     * perndryshe njofton userin qe nuk eshte i autorizuar
     * @param requestJwt tokeni authentifikues
     * @return Userat, ose mesazhi njoftues
     */
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(@RequestHeader("Authorization") String requestJwt) {
        List<User> users = userService.getAllUsers(requestJwt);
        return this.ok(users);
    }

    /**
     * Kjo Metode eshte endpoint e cila kthen te userin ne baze te userId-se ne qofte se useri qe e invokon
     * ka te drejte te inspektoj ate user. Perndryshe njofton userin qe nuk eshte i autorizuar.
     * @param requestJwt tokeni authentifikues
     * @return Userin, ose mesazhis njoftues
     */
    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(
            @PathVariable("userId") Long viewUserId,
            @RequestHeader("Authorization") String requestJwt) {
        User user = userService.getUserById(viewUserId, requestJwt);
        return this.ok(user);
    }

    /**
     * Kjo metode eshte endpoint e cila fshine user ne baze te ID-se se tij ne qofte eshte i autorizuar.
     * Nje user konsiderohet i autorizuar ne momentin qe eshte admin, ose tenton te fshij veten.
     * @param deleteUserId ID e userit per t'u fshire
     * @param requestJwt JWT tokeni authentifikues per userin qe ben kerkesen
     * @return - Mesazh konfirmues se a eshte fshire useri, ne baze te
     */
    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUser(
            @PathVariable("userId") Long deleteUserId,
            @RequestHeader("Authorization") String requestJwt) {
        this.userService.deleteUser(deleteUserId, requestJwt);
        return this.ok("Perdoruesi u fshi me sukses");
    }

    /**
     * Kjo metode eshte endpoint e cila e kyc userin ne sistem permes JWT tokenit.
     * @param loginRequest rekord, enkapsulimi i userId dhe password-it
     * @return JWT token te userit, ose njoftim ne rast qe kredencialet jane gabim.
     */
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        return this.ok(this.userService.login(loginRequest.id(), loginRequest.password()));
    }

    @GetMapping("{id}/history")
    public ResponseEntity<String> getUserHistory(
            @PathVariable Long id,
            @RequestHeader("Authorization") String requestJwt) {
        return this.ok(this.userService.getUserHistory(id, requestJwt));
    }

}
