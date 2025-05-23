package edu.unipr.eshendetsia.service.implementation;

import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import edu.unipr.eshendetsia.exception.concrete.NotFoundException;
import edu.unipr.eshendetsia.exception.concrete.UnauthorizedException;
import edu.unipr.eshendetsia.model.entity.Allergy;
import edu.unipr.eshendetsia.model.entity.User;
import edu.unipr.eshendetsia.model.entity.UserAllergy;
import edu.unipr.eshendetsia.repository.AllergyRepository;
import edu.unipr.eshendetsia.repository.UserAllergyRepository;
import edu.unipr.eshendetsia.service.interfaces.AllergyService;
import edu.unipr.eshendetsia.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Implementimi i sherbimit per menaxhimin e alergjive
 * perfshin logjiken kryesore te biznesit per te gjitha operacionet
 * qe lidhen me alergji
 */
@Service
public class AllergyServiceImplementation implements AllergyService {

    private final UserAllergyRepository userAllergyRepository;
    private final AllergyRepository allergyRepository;

    private final UserService userService;

    /**
     * Konstruktori i klases
     *
     * @param userAllergyRepository repository per akses ne te dhenat e alergjive
     */
    @Autowired
    public AllergyServiceImplementation(UserAllergyRepository userAllergyRepository, AllergyRepository allergyRepository, UserService userService) {
        this.userAllergyRepository = userAllergyRepository;
        this.allergyRepository = allergyRepository;
        this.userService = userService;
    }

    /**
     * Ruan nje alergji te re ne sistem
     *
     * @param allergy objekti i alergjise per tu ruajtur
     * @return alergji e ruajtur
     * @throws JWTVerificationException Nese JWT tokeni nuk eshte valid
     * @throws UnauthorizedException Nese perdoruesi nuk eshte i autorizuar
     */
    public Allergy save(Allergy allergy, String authHeader) throws UnauthorizedException {
        DecodedJWT decodedJWT = JWT.decode(authHeader);
        Long userId = Long.parseLong(decodedJWT.getSubject());

        User user = userService.getUserById(userId, authHeader);

        if (user.isAdmin())
            throw new UnauthorizedException("Nuk jeni i autorizuar");

        return allergyRepository.save(allergy);
    }

    /**
     * Merr te gjitha alergjite e nje perdoruesi
     *
     * @param allergyUserId ID e perdoruesit
     * @return lista e alergjive
     * @throws JWTVerificationException Nese JWT tokeni nuk eshte valid
     * @throws UnauthorizedException    Nese perdoruesi nuk eshte i autorizuar
     * @throws NotFoundException        Nese alergjia nuk eshte gjetur
     */
    public List<UserAllergy> getByUserId(Long allergyUserId, String authHeader) throws UnauthorizedException{
        DecodedJWT decodedJWT = JWT.decode(authHeader);
        Long userId = Long.parseLong(decodedJWT.getSubject());

        if (!userId.equals(allergyUserId)) // userId != allergyUserId
            throw new UnauthorizedException("Nuk jeni i autorizuar");

        User user = new User();
        user.setId(userId);

        Optional<List<UserAllergy>> allergyList = userAllergyRepository.findByUser(user);

        if (allergyList.isEmpty())
            throw new NotFoundException("Useri nuk ka alergji");

        return allergyList.get();
    }

    /**
     * Fshin nje alergji nga sistemi
     *
     * @param id ID e alergjise per tu fshire
     * @throws JWTVerificationException Nese JWT tokeni nuk eshte valid
     * @throws UnauthorizedException Nese perdoruesi nuk eshte i autorizuar
     * @throws NotFoundException Nese alergjia nuk eshte gjetur
     */
    public void delete(Long id, String authHeader) throws UnauthorizedException, NotFoundException {
        DecodedJWT decodedJWT = JWT.decode(authHeader);
        Long userId = Long.parseLong(decodedJWT.getSubject());

        User user = userService.getUserById(userId, authHeader);

        if (user.isAdmin())
            throw new UnauthorizedException("Nuk jeni i autorizuar");

        userAllergyRepository.deleteById(id);
    }
}
