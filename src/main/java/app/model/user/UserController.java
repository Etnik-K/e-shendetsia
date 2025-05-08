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

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{userId}")
    public ResponseEntity<ApiResponse<User>> getUserById(@PathVariable Long userId) {
        Optional<User> user = userService.getUserById(userId);
        return user.map(this::ok).orElseGet(() -> this.error(STR."Useri me ID \{userId} nuk eksiston"));
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse<String>> deleteUser(@PathVariable long deleteUserId, @RequestHeader("Authorization") String requestJwt) {
        try{
            DecodedJWT jwt = JWTUtil.verifyToken(requestJwt);
            long userId = Long.parseLong(jwt.getSubject());
            // if ( deleteuserid == userid || role service . get role (userId) . equals ("admin") )
            // userService.deleteUser(delteuserid);
            return this.ok("Perdoruesi u fshi me sukses");
        } catch (JWTVerificationException j) {
            return this.error("Ani kishe une pe forgi tokenin");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<String>> login(@RequestBody LoginRequest loginRequest) {
        System.out.println(STR."Prsh nga /api/login endpoint! - UserLoginDTO eshte: \{loginRequest} - tu auth pa hash");
        User validUser = userService.authenticateNoHash(loginRequest.id(), loginRequest.password());
//        User validUser = userService.authenticate(userLoginDTO.id(), userLoginDTO.password());

        if (validUser == null) {
            return this.error("Perdoruesi/Fjalekalimi eshte gabim.");
        }

        System.out.println(STR."validUser: \{validUser.toString()}");
        // jwt

        HashMap<String, String> claims = new HashMap<>();
        claims.put("role", validUser.getRole().getName());

        String token = JWTUtil.createToken(claims, validUser.getId());

        return this.ok(token);
    }
}
