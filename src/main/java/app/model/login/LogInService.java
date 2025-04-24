package app.model.login;

import app.model.user.UserService;
import app.util.PasswordHasher;
import org.springframework.stereotype.Service;

@Service
public class LogInService {

        private final UserService userService;
        public LogInService(UserService userService) {
            this.userService = userService;
        }
    public boolean checkLogin(String username, String password) {
        String salt = userService.getSaltByUsername(username);
        String hash = userService.getHashByUsername(username);

        if (salt != null && hash != null) {
            return PasswordHasher.compareSaltedHash(password, salt, hash);
        }
        return false;
    }
}
