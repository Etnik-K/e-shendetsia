package app.model.user;

import app.dto.request.LoginRequest;
import app.util.ApiResponse;
import app.util.BaseController;
import app.util.JWTUtil;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

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
            DecodedJWT jwt = JWTUtil.verifyToken(requestJwt);
            long userId = Long.parseLong(jwt.getSubject());

            if (!userService.getRoleById(userId).getName().equals("admin"))
                return this.error("Nuk jeni i autorizuar!");

            List<User> users = userService.getAllUsers();
            ApiResponse<List<User>> apiResponse = new ApiResponse<>(true, users, null);
            return ResponseEntity.ok(apiResponse);
        } catch (JWTVerificationException j) {
            return this.error("JWT eshte i pavleftshem!");
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
            DecodedJWT jwt = JWTUtil.verifyToken(requestJwt);
            long userId = Long.parseLong(jwt.getSubject());

            if (!(viewUserId == userId || userService.getRoleById(userId).getName().equals("admin")))
                return this.error("Nuk jeni i autorizuar!");

            Optional<User> optUser = userService.getUserById(userId);
            if (!optUser.isPresent())
                return this.error("Perdoruesi nuk u gjet");
            User user = optUser.get();
            ApiResponse<User> apiResponse = new ApiResponse<>(true, user, null);
            return ResponseEntity.ok(apiResponse);
        } catch (JWTVerificationException j) {
            return this.error("JWT eshte i pavleftshem!");
        }
    }

    // me gjase duhet me fshi
    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    /**
     * Kjo metode eshte endpoint e cila fshine user ne baze te ID-se se tij ne qofte eshte i autorizuar.
     * Nje user konsiderohet i autorizuar ne momentin qe eshte admin, ose tenton te fshij veten.
     * @param deleteUserId ID e userit per t'u fshire
     * @param requestJwt JWT tokeni authentifikues per userin qe ben kerkesen
     * @return - Mesazh konfirmues se a eshte fshire useri, ne baze te
     */
    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse<String>> deleteUser(@PathVariable("userId") long deleteUserId, @RequestHeader("Authorization") String requestJwt) {
        try{
            DecodedJWT jwt = JWTUtil.verifyToken(requestJwt);
            long userId = Long.parseLong(jwt.getSubject());

            if (!(deleteUserId == userId || userService.getRoleById(userId).getName().equals("admin")))
                return this.error("Nuk jeni i autorizuar!");

            userService.deleteUser(deleteUserId);
            return this.ok("Perdoruesi u fshi me sukses");
        } catch (JWTVerificationException j) {
            return this.error("JWT eshte i pavleftshem!");
        }
    }

    /**
     * Kjo metode eshte endpoint e cila e kyc userin ne sistem permes JWT tokenit.
     * @param loginRequest rekord, enkapsulimi i userId dhe password-it
     * @return JWT token te userit, ose njoftim ne rast qe kredencialet jane gabim.
     */
    @PostMapping("/login")
    public ResponseEntity<ApiResponse<String>> login(@RequestBody LoginRequest loginRequest) {
        System.out.println(STR."Prsh nga /api/login endpoint! - UserLoginDTO eshte: \{loginRequest} - tu auth pa hash");
        User validUser = userService.authenticateNoHash(loginRequest.id(), loginRequest.password());
//        User validUser = userService.authenticate(userLoginDTO.id(), userLoginDTO.password());

        if (validUser == null) {
            return this.error("Perdoruesi/Fjalekalimi eshte gabim.");
        }

        HashMap<String, String> claims = new HashMap<>();
        String token = JWTUtil.createToken(claims, validUser.getId());

        return this.ok(token);
    }
}