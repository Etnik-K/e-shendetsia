package app.model.user;

import app.dto.request.LoginRequest;
import app.exception.InvalidCredentialsException;
import app.exception.NotFoundException;
import app.exception.UnauthorizedException;
import app.util.ApiResponse;
import app.util.BaseController;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
     * Kjo Metode eshte endpoint e cila kthen te gjithe userat ne qofte se useri qe e invokon eshte admin, perndryshe njofton userin qe nuk eshte i autorizuar
     * @param requestJwt tokeni authentifikues
     * @return Userat, ose mesazhi njoftues
     */
    @GetMapping
    public ResponseEntity<ApiResponse<List<User>>> getAllUsers(@RequestHeader("Authorization") String requestJwt) {
        try{
            List<User> users = userService.getAllUsers(requestJwt);
            return this.ok(users);
        } catch (JWTVerificationException  | UnauthorizedException exception) {
            return this.error("Nuk jeni i autorizuar!", HttpStatus.UNAUTHORIZED);
        }
    }

    /**
     * Kjo Metode eshte endpoint e cila kthen te userin ne baze te userId-se ne qofte se useri qe e invokon eshte ka te drejte te inspektoj ate user. Perndryshe njofton userin qe nuk eshte i autorizuar.
     * @param requestJwt tokeni authentifikues
     * @return Userin, ose mesazhis njoftues
     */
    @GetMapping("/{userId}")
    public ResponseEntity<ApiResponse<User>> getUserById(@PathVariable("userId") Long viewUserId, @RequestHeader("Authorization") String requestJwt) {
        try{
            User user = userService.getUserById(viewUserId, requestJwt);
            return this.ok(user);
        } catch (JWTVerificationException | UnauthorizedException exception) {
            return this.error("Nuk jeni i autorizuar", HttpStatus.UNAUTHORIZED);
        } catch (NotFoundException exception) {
            return this.error("Nuk u gjet useri", HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Kjo metode eshte endpoint e cila fshine user ne baze te ID-se se tij ne qofte eshte i autorizuar.
     * Nje user konsiderohet i autorizuar ne momentin qe eshte admin, ose tenton te fshij veten.
     * @param deleteUserId ID e userit per t'u fshire
     * @param requestJwt JWT tokeni authentifikues per userin qe ben kerkesen
     * @return - Mesazh konfirmues se a eshte fshire useri, ne baze te
     */
    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse<String>> deleteUser(@PathVariable("userId") Long deleteUserId, @RequestHeader("Authorization") String requestJwt) {
        try{
            this.userService.deleteUser(deleteUserId, requestJwt);
            return this.ok("Perdoruesi u fshi me sukses");
        } catch (JWTVerificationException | UnauthorizedException exception) {
            return this.error("Nuk jeni i autorizuar", HttpStatus.UNAUTHORIZED);
        }
    }

    /**
     * Kjo metode eshte endpoint e cila e kyc userin ne sistem permes JWT tokenit.
     * @param loginRequest rekord, enkapsulimi i userId dhe password-it
     * @return JWT token te userit, ose njoftim ne rast qe kredencialet jane gabim.
     */
    @PostMapping("/login")
    public ResponseEntity<ApiResponse<String>> login(@RequestBody LoginRequest loginRequest) {
        try{
            return this.ok(this.userService.login(loginRequest.id(), loginRequest.password()));
        } catch (NotFoundException | InvalidCredentialsException exception) {
            return this.error("Perdoruese/Fjalekalimi i gabuar", HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("{id}/history")
    public ResponseEntity<ApiResponse<User>> getUserHistory(@PathVariable Long id, @RequestHeader("Authorization") String requestJwt) {
        try {
            return this.ok(this.userService.getUserHistory(id, requestJwt));
        } catch (JWTVerificationException | UnauthorizedException e) {
            return this.error("Nuk jeni i autorizuar", HttpStatus.UNAUTHORIZED);
        } catch (NotFoundException exception) {
            return this.error("Nuk u gjet useri", HttpStatus.NOT_FOUND);
        }
    }

}
