package edu.unipr.eshendetsia.service.implementation;

import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTDecodeException;
import edu.unipr.eshendetsia.exception.concrete.NotFoundException;
import edu.unipr.eshendetsia.exception.concrete.UnauthorizedException;
import edu.unipr.eshendetsia.model.entity.Insurance;
import edu.unipr.eshendetsia.model.entity.User;
import edu.unipr.eshendetsia.repository.InsuranceRepository;
import edu.unipr.eshendetsia.service.interfaces.InsuranceService;
import edu.unipr.eshendetsia.service.interfaces.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Implementimi i sherbimit te sigurimit
 * Menaxhon operacionet CRUD per sigurimet
 */
@AllArgsConstructor
@Service
public class InsuranceServiceImplementation implements InsuranceService {

    private final InsuranceRepository insuranceRepository;

    private final UserService userService;

    /**
     * Ruan nje sigurim te ri
     *
     * @param insurance sigurimi qe do te ruhet
     * @throws UnauthorizedException Nese useri tenton te vendose insurance qe nuk i takon
     * @throws NumberFormatException probleme me JWT
     * @throws JWTDecodeException probleme me JWT
     */
    public void save(Insurance insurance, String authHeader) throws UnauthorizedException, NumberFormatException, JWTDecodeException {
        Long userId = Long.parseLong(JWT.decode(authHeader).getSubject());

        User user = this.userService.getUserById(userId);

        if (!insurance.getUser().equals(user))
            throw new UnauthorizedException("Nuk jeni i autorizuar");

        insuranceRepository.save(insurance);
    }

    /**
     * Merr te gjitha sigurimet e nje perdoruesi
     *
     * @param viewUserId ID e perdoruesit
     * @return lista e sigurimeve
     * @throws UnauthorizedException Nese useri tenton te lexoj insurance qe nuk i takon
     * @throws NumberFormatException probleme me JWT
     * @throws JWTDecodeException probleme me JWT
     */
    public List<Insurance> getByUserId(Long viewUserId, String authHeader) throws UnauthorizedException, NumberFormatException, JWTDecodeException{
        Long userId = Long.parseLong(JWT.decode(authHeader).getSubject());

        User user = this.userService.getUserById(userId);

        if (!user.isAdmin() && !user.getId().equals(userId))
            throw new UnauthorizedException("Nuk jeni i autorizuar");

        return insuranceRepository.findByUserId(userId);
    }

    /**
     * Perditeson statusin e nje sigurimi
     *
     * @param id ID e sigurimit
     * @param active statusi i ri
     * @throws UnauthorizedException Ne qofte se nje jo-admin tenton te nderroj gjendjen e nje insurance
     * @throws NotFoundException ne qofte se insurance nuk ekziston
     * @throws NumberFormatException probleme me JWT
     * @throws JWTDecodeException probleme me JWT
     */
    public void updateStatus(Long id, boolean active, String authHeader) throws NumberFormatException, JWTDecodeException, UnauthorizedException, NotFoundException{
        Long userId = Long.parseLong(JWT.decode(authHeader).getSubject());

        User user = this.userService.getUserById(userId);

        if (!user.isAdmin())
            throw new UnauthorizedException("Nuk jeni i autorizuar");

        Optional<Insurance> optInsurance = insuranceRepository.findById(id);

        if (optInsurance.isEmpty())
            throw new NotFoundException("Insurance nuk ekziston");

        Insurance insurance = optInsurance.get();

        insurance.setActive(active);

        this.insuranceRepository.save(insurance);
    }

    /**
     * Fshin nje sigurim
     *
     * @param id ID e sigurimit qe do te fshihet
     * @throws UnauthorizedException Ne qofte se nje jo-admin tenton te fshij gjendjen e nje insurance qe nuk i takon
     * @throws NumberFormatException probleme me JWT
     * @throws JWTDecodeException probleme me JWT
     */
    public void delete(Long id, String authHeader) throws NumberFormatException, JWTDecodeException, UnauthorizedException{
        Long userId = Long.parseLong(JWT.decode(authHeader).getSubject());

        User user = this.userService.getUserById(userId);

        if (!user.isAdmin() && !user.getId().equals(userId))
            throw new UnauthorizedException("Nuk jeni i autorizuar");

        insuranceRepository.deleteById(id);
    }
}
