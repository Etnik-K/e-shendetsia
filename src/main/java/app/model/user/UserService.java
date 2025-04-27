package app.model.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public void deleteUser(long id) {
        userRepository.deleteById(id);
    }

    public String getHashById(long id) {
        Optional<User> user = userRepository.findById(id);
        return user.map(User::getPasswordHash).orElse(null); // if user is present return user.get.getpasswordhash jojo, null babo
    }

    public String getSaltById(long id) {
        Optional<User> user = userRepository.findById(id);
        return user.map(User::getPasswordHash).orElse(null);
    }
}