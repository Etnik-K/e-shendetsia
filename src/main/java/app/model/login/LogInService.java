package app.model.login;

import app.model.user.UserService;
import app.util.Hasher;
import org.springframework.stereotype.Service;

@Service
public class LogInService {

    private final UserService userService;
    public LogInService(UserService userService) {
        this.userService = userService;
    }
    public boolean checkLogin(long id, String password) {
        String salt = userService.getSaltById(id);
        String hash = userService.getHashById(id);

        if (salt != null && hash != null) {
            return Hasher.compareSaltedHash(password, salt, hash);
        }
        return false;
    }
}
