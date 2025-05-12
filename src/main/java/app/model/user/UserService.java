package app.model.user;

import app.exception.InvalidCredentialsException;
import app.exception.NotFoundException;
import app.exception.UnauthorizedException;
import app.util.Hasher;
import app.util.JWTUtil;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Kjo metode eshte implementimi i logjikes per endpointin GET /api/users/
     * @param requestJwt Tokeni authentifikues
     * @return Nje Liste me te gjithe Userat
     * @throws UnauthorizedException Ne momentin kur Useri qe e invokon metoden nuk ka privilegje te adminit
     * @throws JWTVerificationException Ne momentin kur kemi token invalid
     */
    public List<User> getAllUsers(String requestJwt) throws UnauthorizedException, JWTVerificationException {

        DecodedJWT jwt = JWTUtil.verifyToken(requestJwt);
        long userId = Long.parseLong(jwt.getSubject());

        if (!this.userRepository.getRoleById(userId).getName().equals("admin"))
            throw new UnauthorizedException();

        return this.userRepository.findAll();

    }

    /**
     * Kjo metode eshte implementimi i logjikes per endpointin GET /api/users/{viewUserId}
     * @param viewUserId ID e Userit per t'u kthyer
     * @param requestJwt Tokeni authentifikues
     * @return Userin me ID perkatese
     * @throws UnauthorizedException Ne momentin kur nje User tenton qe lexoj nje profil per te cilin nuk eshte i autorizuar
     * @throws JWTVerificationException Ne momentin kur kemi token invalid
     * @throws NotFoundException ne momenitn kur nuk ka profil me id viewUserId
     */
    public User getUserById(long viewUserId, String requestJwt) throws UnauthorizedException, JWTVerificationException, NotFoundException {

        DecodedJWT jwt = JWTUtil.verifyToken(requestJwt);
        long userId = Long.parseLong(jwt.getSubject());

        if (!(viewUserId == userId || this.userRepository.getRoleById(userId).getName().equals("admin")))
            throw new UnauthorizedException();

        Optional<User> optUser = this.userRepository.findById(userId);

        if (optUser.isEmpty())
            throw new NotFoundException("Useri nuk u gjet");

        return optUser.get();

    }

    /**
     * Kjo metode eshte implementimi i logjikes per endpointin DELETE /api/users/{deleteUserId}
     * @param deleteUserId ID per fshirje
     * @param requestJwt Tokeni authentifikues
     * @return Mesazh konfirmues
     * @throws UnauthorizedException Ne momentin kur nje user tentoj te fshije nje llogari mbi te cilat nuk ka qasje administrative
     * @throws JWTVerificationException Ne momentin qe tokeni eshte i skaduar, apo jo-valid
     */
    public String deleteUser(long deleteUserId, String requestJwt) throws UnauthorizedException, JWTVerificationException {

        DecodedJWT jwt = JWTUtil.verifyToken(requestJwt);
        long userId = Long.parseLong(jwt.getSubject());

        if (!(deleteUserId == userId || this.userRepository.getRoleById(userId).getName().equals("admin")))
            throw new UnauthorizedException("Nuk jeni i autorizuar!");

        this.userRepository.deleteById(deleteUserId);
        return "Perdoruesi u fshi me sukses";
    }

    /**
     * Kjo metode eshte implementimi i logjikes per endpointin POST /api/users/login
     * @param id ID e userit
     * @param password Fjalekalimi
     * @return JWT tokenin authentifikues
     * @throws NotFoundException Ne rast te userit jo ekzistent
     * @throws InvalidCredentialsException Ne rast te fjalekalimit te gabuar
     */
    public String login(long id, String password) throws InvalidCredentialsException, NotFoundException{
        User validUser = this.authenticateNoHash(id, password);
//        User validUser = this.authenticate(id, password);

        HashMap<String, String> claims = new HashMap<>();
        claims.put("first_name", validUser.getFirstName());
        claims.put("last_name", validUser.getLastName());
        claims.put("email", validUser.getEmail());

        return JWTUtil.createToken(claims, validUser.getId());
    }

    /**
     * Kjo metode shfrytzohet per me authentifiku nje user
     * @param id ID e userit
     * @param password fjalekalimi i userit
     * @return Userin me id perkatese nese ka sukses, pperndryshe null
     */
    private User authenticate(long id, String password) throws NotFoundException, InvalidCredentialsException {

        Optional<User> user = this.userRepository.findById(id);

        if (user.isEmpty()) throw new NotFoundException("Useri nuk u gjet");

        String salt = user.map(User::getPassword).orElse(null);
        String passwordHash = Hasher.generateSaltedHash(password, salt);

        if (!passwordHash.equals(user.get().getPassword())) throw new InvalidCredentialsException();

        return user.get();
    }

    /**
     * Kjo metode shfrytzohet eksluzivisht per perdorim zhvillues.
     * Me qellim per me testu loginin permes insertimit te userave pa passwordav te hashuar direkt ne databaze.
     * @param id ID e userit
     * @param password fjalekalimi i userit
     * @return Userin me id perkatese nese ka sukses, pperndryshe null
     */
    private User authenticateNoHash(long id, String password) throws InvalidCredentialsException, NotFoundException {
        Optional<User> user = this.userRepository.findById(id);

        if (user.isEmpty()) throw new NotFoundException("Useri nuk u gjet");

        if (!password.equals(user.get().getPassword())) throw new InvalidCredentialsException();

        return user.get();
    }

    /**
    * Kjo metode shfrytezohet per caching
    * Me qellim per me ruajt historine e userit ne cache
     * @param userId ID e userit
     * @return Historine e userit
    *
     * */
    @Cacheable(value = "history", key = "#userId")
    public Optional<User> getUserHistory(long userId) {
        System.out.println("⏳ Fetching user from DB for ID: " + userId);
        return userRepository.findById(userId);
    }

}