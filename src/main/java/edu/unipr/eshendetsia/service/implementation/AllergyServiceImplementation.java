package edu.unipr.eshendetsia.service.implementation;

import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import edu.unipr.eshendetsia.exception.concrete.NotFoundException;
import edu.unipr.eshendetsia.exception.concrete.UnauthorizedException;
import edu.unipr.eshendetsia.model.entity.Allergy;
import edu.unipr.eshendetsia.model.entity.User;
import edu.unipr.eshendetsia.repository.AllergyRepository;
import edu.unipr.eshendetsia.service.interfaces.AllergyService;
import edu.unipr.eshendetsia.service.interfaces.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Implementimi i sherbimit per menaxhimin e alergjive
 * perfshin logjiken kryesore te biznesit per te gjitha operacionet
 * qe lidhen me alergji
 */
@AllArgsConstructor
@Service
public class AllergyServiceImplementation implements AllergyService {

    private final AllergyRepository allergyRepository;

    private final UserService userService;


    /**
     * Ruan nje alergji te re ne sistem
     *
     * @param allergy objekti i alergjise per tu ruajtur
     * @throws JWTVerificationException Nese JWT tokeni nuk eshte valid
     * @throws UnauthorizedException Nese perdoruesi nuk eshte i autorizuar
     */
    public void save(Allergy allergy, String authHeader) throws UnauthorizedException, NotFoundException, JWTDecodeException, NumberFormatException {
        Long userId = Long.parseLong(JWT.decode(authHeader).getSubject());

        User user = this.userService.getUserById(userId);

        if (!user.isAdmin())
            throw new UnauthorizedException("Nuk jeni i autorizuar");

        allergyRepository.save(allergy);
    }
}
