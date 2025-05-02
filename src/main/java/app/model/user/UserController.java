package app.model.user;

import app.util.ApiResponse;
import app.util.BaseController;
import app.util.JWTUtil;
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
        return user.map(this::ok).orElseGet(() -> this.notFound(STR."Useri me ID \{userId} nuk eksiston"));
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse<User>> deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.ok().build();
    }
    // ----------- nashta duhet me hi me ni login controller vet
    @PostMapping("/login")
    public ResponseEntity<ApiResponse<String>> login(@RequestBody User user) {

        User validUser = userService.authenticate(user.getId(), user.getPassword());

        if (validUser == null) {
            return this.notFound(STR."Perdoruesi/Fjalekalimi eshte gabim.");
        }

        // jwt
        HashMap<String, String> claims = new HashMap<>();

        claims.put("role", validUser.getRole());

        String token = JWTUtil.createToken(claims, validUser.getId());

        return ResponseEntity.ok(new ApiResponse<>(true, token, null));
    }
}
