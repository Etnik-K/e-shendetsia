package edu.unipr.eshendetsia.service.interfaces;

import edu.unipr.eshendetsia.exception.concrete.InvalidCredentialsException;
import edu.unipr.eshendetsia.exception.concrete.NotFoundException;
import edu.unipr.eshendetsia.exception.concrete.UnauthorizedException;
import edu.unipr.eshendetsia.model.entity.Role;
import edu.unipr.eshendetsia.model.entity.User;

import java.util.List;
import java.util.Set;

public interface UserService {

    /**
     * Kjo metode eshte implementimi i logjikes per endpointin GET /api/users/
     * @param requestJwt Tokeni authentifikues
     * @return Nje Liste me te gjithe Userat
     */
    List<User> getAllUsers(String requestJwt) throws UnauthorizedException;

    /**
     * Kjo metode eshte implementimi i logjikes per endpointin GET /api/users/{viewUserId}
     * @param viewUserId ID e Userit per t'u kthyer
     * @param requestJwt Tokeni authentifikues
     * @return Userin me ID perkatese
     */
    User getUserById(Long viewUserId, String requestJwt) throws UnauthorizedException, NotFoundException;

    /**
     * Kjo metode eshte implementimi i logjikes per endpointin DELETE /api/users/{deleteUserId}
     * @param deleteUserId ID per fshirje
     * @param requestJwt Tokeni authentifikues
     */
    void deleteUser(Long deleteUserId, String requestJwt) throws UnauthorizedException;

    /**
     * Kjo metode eshte implementimi i logjikes per endpointin POST /api/users/login
     * @param id ID e userit
     * @param password Fjalekalimi
     * @return JWT tokenin authentifikues
     */
    String login(Long id, String password) throws InvalidCredentialsException, NotFoundException;

    String getUserHistory(Long id, String requestJwt) throws UnauthorizedException, NotFoundException;

    User getUserByIdForServer(Long id) throws NotFoundException;

    /**
     * Kthen nje set roles
     * @param userId Useri, rolet e te cilit na duhen.
     * @return Seti me rolet e userit me id perkatese.
     */
    Set<Role> findRolesById(Long userId, String authHeader) throws NotFoundException;
}