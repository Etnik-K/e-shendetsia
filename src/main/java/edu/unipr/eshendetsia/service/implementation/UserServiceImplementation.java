package edu.unipr.eshendetsia.service.implementation;

import edu.unipr.eshendetsia.exception.concrete.InvalidCredentialsException;
import edu.unipr.eshendetsia.exception.concrete.NotFoundException;
import edu.unipr.eshendetsia.exception.concrete.UnauthorizedException;
import edu.unipr.eshendetsia.model.entity.Role;
import edu.unipr.eshendetsia.model.entity.User;
import edu.unipr.eshendetsia.repository.UserRepository;
import edu.unipr.eshendetsia.service.interfaces.UserService;
import edu.unipr.eshendetsia.service.interfaces.JWTService;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import edu.unipr.eshendetsia.service.interfaces.HasherService;


import com.auth0.jwt.JWT;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImplementation implements UserService {

    private final UserRepository userRepository;
    private final JWTService jwtService;
    private final HasherService hasherService;

    @Autowired
    public UserServiceImplementation(UserRepository userRepository, JWTService jwtService, HasherService hasherService) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.hasherService = hasherService;
    }

    /**
     * Kjo metode eshte implementimi i logjikes per endpointin GET /api/users/
     * @param requestJwt Tokeni authentifikues
     * @return Nje Liste me te gjithe Userat
     * @throws UnauthorizedException Ne momentin kur Useri qe e invokon metoden nuk ka privilegje te adminit
     * @throws JWTVerificationException Ne momentin kur kemi token invalid
     */
    public List<User> getAllUsers(String requestJwt) throws UnauthorizedException {

        DecodedJWT jwt = JWT.decode(requestJwt);
        long userId = Long.parseLong(jwt.getSubject());

        // kushtin duhet me zv me !this.isAdmin(userId)
        Optional<User> optUser = this.userRepository.findById(userId);
        if (optUser.isEmpty())
            throw new UnauthorizedException("Nuk jeni i autorizuar!");

        if (optUser.get().getRoles().stream().noneMatch(role -> role.getName().equalsIgnoreCase("admin")))
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
    public User getUserById(Long viewUserId, String requestJwt) throws UnauthorizedException, NotFoundException {

        DecodedJWT jwt = JWT.decode(requestJwt);
        long userId = Long.parseLong(jwt.getSubject());

        Optional<User> optUser = this.userRepository.findById(viewUserId);
        if (optUser.isEmpty())
            throw new NotFoundException("Nuk jeni i autorizuar!");

        if (!(optUser.get().getRoles().stream().noneMatch(role -> role.getName().equalsIgnoreCase("ADMIN")) || (viewUserId == userId)))
            throw new UnauthorizedException("Nuk jeni i autorizuar!");


        return optUser.get();

    }

    /**
     * Kjo n'kushte normale ka me u fshi.
     * .
     * .
     * .
     * Kjo metode kthen userin me id perkatese. Nuk kerkon authentikim. Perdoret vetem nga serveri per qellime te brendshme dhe *NUK DUHET* te ekspozohet ne endpointa
     * @param id ID e userit per me kthy
     * @return Useri me ID perkatese
     * @throws NotFoundException Ne momentin kur useri me ID perkatese nuk eksiston
     */
    public User getUserByIdForServer(Long id) throws NotFoundException {
        Optional<User> validUser = this.userRepository.findById(id);

        if (validUser.isEmpty())
            throw new NotFoundException("Useri nuk u gjet");

        return validUser.get();
    }

    /**
     * Kthen setin e roleve te userit, sipas ID-se
     *
     * @param userId Useri, rolet e te cilit na duhen.
     * @return Seti me rolet e userit me id perkatese.
     */
    public Set<Role> findRolesById(Long userId, String authHeader) throws NotFoundException {
        Long requestUserId = Long.parseLong(JWT.decode(authHeader).getSubject());

        User user = this.getUserById(userId);

        if (!requestUserId.equals(userId) && !user.isAdmin())
            throw new UnauthorizedException("Nuk jeni i autorizuar!");

        return user.getRoles();
    }

    /**
     * Kjo metode eshte implementimi i logjikes per endpointin DELETE /api/users/{deleteUserId}
     * @param deleteUserId ID per fshirje
     * @param requestJwt Tokeni authentifikues
     * @throws UnauthorizedException Ne momentin kur nje user tentoj te fshije nje llogari mbi te cilat nuk ka qasje administrative
     * @throws JWTVerificationException Ne momentin qe tokeni eshte i skaduar, apo jo-valid
     */
    public void deleteUser(Long deleteUserId, String requestJwt) throws UnauthorizedException {
        Long requestUserId = Long.parseLong(JWT.decode(requestJwt).getSubject());

        User user = this.getUserById(requestUserId);

        if (!user.isAdmin() && !deleteUserId.equals(requestUserId))
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

        return this.jwtService.createToken(claims, validUser.getId());
    }

    /**
     * Kjo metode shfrytzohet per me authentifiku nje user
     * @param id ID e userit
     * @param password fjalekalimi i userit
     * @return Userin me id perkatese nese ka sukses, pperndryshe null
     */
    private User authenticate(Long id, String password) throws NotFoundException, InvalidCredentialsException {
        User user = this.getUserById(id);

        String salt = user.getSalt(); // This assumes User has a getSalt() method
        String passwordHash = this.hasherService.generateSaltedHash(password, salt);

        if (!passwordHash.equals(user.getPassword())) {
            throw new InvalidCredentialsException();
        }

        return user;
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
    public String getUserHistory(Long userId, String requestJwt) throws UnauthorizedException, NotFoundException {
        DecodedJWT jwt = JWT.decode(requestJwt);
        Long jwtSubject = Long.valueOf(jwt.getSubject());

        User user = this.getUserById(userId, requestJwt);

        if (!user.isAdmin() &&
                !jwtSubject.equals(userId))
            throw new UnauthorizedException("Nuk jeni i autorizuar!");

        return user.getHistory();
    }

    private User getUserById(Long id) throws NotFoundException {
        return this.userRepository.findById(id).orElseThrow(() -> new NotFoundException("Useri nuk u gjet"));
    }
}