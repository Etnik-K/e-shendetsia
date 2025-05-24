package edu.unipr.eshendetsia.service.implementation;

import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTDecodeException;
import edu.unipr.eshendetsia.exception.concrete.NotFoundException;
import edu.unipr.eshendetsia.exception.concrete.UnauthorizedException;
import edu.unipr.eshendetsia.model.entity.Bill;
import edu.unipr.eshendetsia.model.entity.User;
import edu.unipr.eshendetsia.repository.BillRepository;
import edu.unipr.eshendetsia.service.interfaces.BillService;
import edu.unipr.eshendetsia.service.interfaces.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Implementimi i sherbimit per faturat
 * Menaxhon operacionet CRUD per faturat
 */
@AllArgsConstructor
@Service
public class BillServiceImplementation implements BillService {

    private final BillRepository billRepository;

    private final UserService userService;

    /**
     * Ruan faturen ne databaze
     *
     * @param bill fatura qe do te ruhet
     * @throws UnauthorizedException nese personi nuk edhte admin
     * @throws JWTDecodeException probleme me JWT
     * @throws NumberFormatException probleme me JWT
     */
    public void save(Bill bill, String requestJwt) throws UnauthorizedException, NumberFormatException, JWTDecodeException {
        Long requestUserId = Long.parseLong(JWT.decode(requestJwt).getSubject());

        User user = this.userService.getUserById(requestUserId);

        if (!user.isAdmin())
            throw new UnauthorizedException("Nuk jeni i autorizuar!");

        this.billRepository.save(bill);
    }

    /**
     * Merr te gjitha faturat e nje perdoruesi
     *
     * @param userId ID e perdoruesit
     * @return lista e faturave te perdoruesit
     * @throws UnauthorizedException nese personi qe nuk eshte admin tenton te lexoj faturat e tjera
     * @throws JWTDecodeException probleme me JWT
     * @throws NumberFormatException probleme me JWT
     */
    public List<Bill> getByUser(Long userId, String requestJwt) throws UnauthorizedException, NumberFormatException, JWTDecodeException{
        Long requestUserId = Long.parseLong(JWT.decode(requestJwt).getSubject());

        User user = this.userService.getUserById(requestUserId);

        if (!user.isAdmin() && !user.getId().equals(userId))
            throw new UnauthorizedException("Nuk jeni i autorizuar!");

        return billRepository.findByUserId(userId);
    }

    /**
     * Merr faturat sipas statusit te pageses
     *
     * @param isPaid statusi i pageses
     * @return lista e faturave sipas statusit
     * @throws UnauthorizedException nese personi nuk edhte admin
     * @throws JWTDecodeException probleme me JWT
     * @throws NumberFormatException probleme me JWT
     */
    public List<Bill> getByPaymentStatus(boolean isPaid, String requestJwt) throws UnauthorizedException, NumberFormatException, JWTDecodeException{
        Long requestUserId = Long.parseLong(JWT.decode(requestJwt).getSubject());

        User user = this.userService.getUserById(requestUserId);

        if (!user.isAdmin())
            throw new UnauthorizedException("Nuk jeni i autorizuar!");

        return billRepository.findByPaid(isPaid);
    }

    /**
     * Shenon faturen si te paguar
     *
     * @param id ID e fature
     * @throws UnauthorizedException nese personi nuk edhte admin
     * @throws NotFoundException nese fatura me ID ne fjale nuk eksiston
     * @throws JWTDecodeException probleme me JWT
     * @throws NumberFormatException probleme me JWT
     */
    public void markAsPaid(Long id, String requestJwt) throws UnauthorizedException, NumberFormatException, JWTDecodeException, NotFoundException{
        Long requestUserId = Long.parseLong(JWT.decode(requestJwt).getSubject());

        User user = this.userService.getUserById(requestUserId);

        if (!user.isAdmin())
            throw new UnauthorizedException("Nuk jeni i autorizuar!");

        Optional<Bill> optBill = billRepository.findById(id);

        if (optBill.isEmpty())
            throw new NotFoundException("Fatura nuk u gjet");

        Bill bill = optBill.get();

        bill.setPaid(true);
        billRepository.save(bill);
    }

    /**
     * Fshin faturen nga databaza
     *
     * @param id ID e fatures per fshirje
     * @throws UnauthorizedException nese personi nuk edhte admin
     * @throws NotFoundException nese nuk eksiston useri m eid perkatese
     * @throws JWTDecodeException probleme me JWT
     * @throws NumberFormatException probleme me JWT
     */
    public void delete(Long id, String requestJwt) throws UnauthorizedException, NumberFormatException, JWTDecodeException, NotFoundException{
        Long requestUserId = Long.parseLong(JWT.decode(requestJwt).getSubject());

        User user = this.userService.getUserById(requestUserId);

        if (!user.isAdmin())
            throw new UnauthorizedException("Nuk jeni i autorizuar!");

        billRepository.deleteById(id);
    }
}
