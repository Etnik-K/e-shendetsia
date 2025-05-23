package edu.unipr.eshendetsia.service.implementation;

import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import edu.unipr.eshendetsia.exception.concrete.NotFoundException;
import edu.unipr.eshendetsia.exception.concrete.UnauthorizedException;
import edu.unipr.eshendetsia.model.entity.User;
import edu.unipr.eshendetsia.model.entity.UserAllergy;
import edu.unipr.eshendetsia.repository.UserAllergyRepository;
import edu.unipr.eshendetsia.service.interfaces.UserAllergyService;
import edu.unipr.eshendetsia.service.interfaces.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class UserAllergyServiceImplementation implements UserAllergyService {

    private final UserAllergyRepository userAllergyRepository;

    private final UserService userService;

    /**
     * Merr te gjitha alergjite e nje perdoruesi
     *
     * @param allergyUserId ID e perdoruesit
     * @return lista e alergjive
     * @throws UnauthorizedException    Nese perdoruesi nuk eshte i autorizuar
     * @throws NotFoundException        Nese alergjia nuk eshte gjetur
     */
    public List<UserAllergy> getByUserId(Long allergyUserId, String authHeader) throws UnauthorizedException, NotFoundException {
        Long userId = Long.parseLong(JWT.decode(authHeader).getSubject());

        if (!userId.equals(allergyUserId) ) // userId != allergyUserId
            throw new UnauthorizedException("Nuk jeni i autorizuar");

        User user = new User();
        user.setId(userId);

        Optional<List<UserAllergy>> allergyList = this.userAllergyRepository.findByUser(user);

        if (allergyList.isEmpty())
            throw new NotFoundException("Useri nuk ka alergji");

        return allergyList.get();
    }

    /**
     * Fshin nje alergji te userit nga sistemi
     *
     * @param id ID e alergjise per tu fshire
     * @throws JWTVerificationException Nese JWT tokeni nuk eshte valid
     * @throws UnauthorizedException Nese perdoruesi nuk eshte i autorizuar
     * @throws NotFoundException Nese alergjia nuk eshte gjetur
     */
    public void delete(Long id, String authHeader) throws UnauthorizedException, NotFoundException {
        DecodedJWT decodedJWT = JWT.decode(authHeader);
        Long userId = Long.parseLong(decodedJWT.getSubject());

        User user = this.userService.getUserById(userId, authHeader);

        if (user.isAdmin())
            throw new UnauthorizedException("Nuk jeni i autorizuar");

        userAllergyRepository.deleteById(id);
    }
}


/*

 */