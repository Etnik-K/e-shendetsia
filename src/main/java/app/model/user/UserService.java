package app.model.user;

import java.util.List;

public interface UserService {

    /**
     * Kjo metode eshte implementimi i logjikes per endpointin GET /api/users/
     * @param requestJwt Tokeni authentifikues
     * @return Nje Liste me te gjithe Userat
     */
    List<User> getAllUsers(String requestJwt);

    /**
     * Kjo metode eshte implementimi i logjikes per endpointin GET /api/users/{viewUserId}
     * @param viewUserId ID e Userit per t'u kthyer
     * @param requestJwt Tokeni authentifikues
     * @return Userin me ID perkatese
     */
    User getUserById(Long viewUserId, String requestJwt);

    /**
     * Kjo metode eshte implementimi i logjikes per endpointin DELETE /api/users/{deleteUserId}
     * @param deleteUserId ID per fshirje
     * @param requestJwt Tokeni authentifikues
     */
    void deleteUser(Long deleteUserId, String requestJwt);

    /**
     * Kjo metode eshte implementimi i logjikes per endpointin POST /api/users/login
     * @param id ID e userit
     * @param password Fjalekalimi
     * @return JWT tokenin authentifikues
     */
    String login(Long id, String password);

    User getUserHistory(Long id, String requestJwt);

    User getUserByIdForServer(Long id);
}