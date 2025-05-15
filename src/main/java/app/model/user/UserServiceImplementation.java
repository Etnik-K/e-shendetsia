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

import com.auth0.jwt.JWT;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImplementation implements UserService {

    private final UserRepository userRepository;
    private final JWTUtil jwtUtil;

    @Autowired
    public UserServiceImplementation(UserRepository userRepository, JWTUtil jwtUtil) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
    }

    /**
     * Kjo metode eshte implementimi i logjikes per endpointin GET /api/users/
     * @param requestJwt Tokeni authentifikues
     * @return Nje Liste me te gjithe Userat
     * @throws UnauthorizedException Ne momentin kur Useri qe e invokon metoden nuk ka privilegje te adminit
     * @throws JWTVerificationException Ne momentin kur kemi token invalid
     */
    public List<User> getAllUsers(String requestJwt) throws UnauthorizedException, JWTVerificationException {

        DecodedJWT jwt = JWT.decode(requestJwt);
        long userId = Long.parseLong(jwt.getSubject());

        // kushtin duhet me zv me !this.isAdmin(userId)
        if (!this.userRepository.getRoleById(userId).getName().equals("admin"))
            throw new UnauthorizedException("Nuk jeni i autorizuar!");

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
    public User getUserById(Long viewUserId, String requestJwt) throws UnauthorizedException, JWTVerificationException, NotFoundException {

        DecodedJWT jwt = JWT.decode(requestJwt);
        long userId = Long.parseLong(jwt.getSubject());

        if (!(this.userRepository.getRoleById(userId).getName().equals("admin") || (viewUserId == userId)))
            throw new UnauthorizedException("Nuk jeni i autorizuar!");

        Optional<User> optUser = this.userRepository.findById(userId);

        if (optUser.isEmpty())
            throw new NotFoundException("Useri nuk u gjet");

        return optUser.get();

    }

    /**
     * Kjo metode kthen userin me id perkatese. Nuk kerkon authentikim. Perdoret vetem nga serveri per qellime te brendshme dhe *NUK DUHET* te ekspozohet ne endpointa
     * @param id ID e userit per me kthy
     * @return Useri me ID perkatese
     * @throws NotFoundException Ne momentin kur useri me ID perkatese nuk eksiston
     */
    @Override
    public User getUserByIdForServer(Long id) throws NotFoundException {
        Optional<User> validUser = this.userRepository.findById(id);

        if (validUser.isEmpty())
            throw new NotFoundException("Useri nuk u gjet");

        return validUser.get();
    }

    /**
     * Kjo metode eshte implementimi i logjikes per endpointin DELETE /api/users/{deleteUserId}
     * @param deleteUserId ID per fshirje
     * @param requestJwt Tokeni authentifikues
     * @throws UnauthorizedException Ne momentin kur nje user tentoj te fshije nje llogari mbi te cilat nuk ka qasje administrative
     * @throws JWTVerificationException Ne momentin qe tokeni eshte i skaduar, apo jo-valid
     */
    public void deleteUser(Long deleteUserId, String requestJwt) throws UnauthorizedException, JWTVerificationException {

        DecodedJWT jwt = JWT.decode(requestJwt);
        long userId = Long.parseLong(jwt.getSubject());

        if (!(deleteUserId == userId) || this.userRepository.getRoleById(userId).getName().equals("admin"))
            throw new UnauthorizedException("Nuk jeni i autorizuar!");

        this.userRepository.deleteById(deleteUserId);
    }

    /**
     * Kjo metode eshte implementimi i logjikes per endpointin POST /api/users/login
     * @param id ID e userit
     * @param password Fjalekalimi
     * @return JWT tokenin authentifikues
     * @throws NotFoundException Ne rast te userit jo ekzistent
     * @throws InvalidCredentialsException Ne rast te fjalekalimit te gabuar
     */
    public String login(Long id, String password) throws InvalidCredentialsException, NotFoundException{
        User validUser = this.authenticateNoHash(id, password);
//        User validUser = this.authenticate(id, password);

        HashMap<String, String> claims = new HashMap<>();
        claims.put("first_name", validUser.getFirstName());
        claims.put("last_name", validUser.getLastName());
        claims.put("email", validUser.getEmail());

        return this.jwtUtil.createToken(claims, validUser.getId());
    }

    /**
     * Kjo metode shfrytzohet per me authentifiku nje user
     * @param id ID e userit
     * @param password fjalekalimi i userit
     * @return Userin me id perkatese nese ka sukses, pperndryshe null
     */
    private User authenticate(Long id, String password) throws NotFoundException, InvalidCredentialsException {

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
    private User authenticateNoHash(Long id, String password) throws InvalidCredentialsException, NotFoundException {
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
     */
    @Cacheable(value = "history", key = "#userId")
    public User getUserHistory(Long userId, String requestJwt) throws JWTVerificationException, UnauthorizedException, NotFoundException {
        DecodedJWT jwt = JWT.decode(requestJwt);
        long jwtSubject = Long.parseLong(jwt.getSubject());

        if (!(this.userRepository.getRoleById(jwtSubject).getName().equals("admin") ||
                jwtSubject == userId))
            throw new UnauthorizedException("Nuk jeni i autorizuar!");

        Optional<User> validUser = this.userRepository.findById(userId);

        if (validUser.isEmpty())
            throw new NotFoundException("User not found");

        User user = validUser.get();
        System.out.println(STR."Returning user: \{user.getId()}, \{user.getEmail()}, \{user.getHistory()}");

        return user;
    }
}