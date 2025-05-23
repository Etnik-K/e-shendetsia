package edu.unipr.eshendetsia.service.interfaces;

import com.auth0.jwt.exceptions.JWTDecodeException;
import edu.unipr.eshendetsia.exception.concrete.NotFoundException;
import edu.unipr.eshendetsia.exception.concrete.UnauthorizedException;
import edu.unipr.eshendetsia.model.entity.Bill;

import java.util.List;

public interface BillService {

    /**
     * Ruan faturen ne databaze
     *
     * @param bill fatura qe do te ruhet
     * @throws UnauthorizedException nese personi nuk edhte admin
     * @throws JWTDecodeException probleme me JWT
     * @throws NumberFormatException probleme me JWT
     */
    void save(Bill bill, String requestJwt) throws UnauthorizedException, NumberFormatException, JWTDecodeException;

    /**
     * Merr te gjitha faturat e nje perdoruesi
     *
     * @param userId ID e perdoruesit
     * @return lista e faturave te perdoruesit
     * @throws UnauthorizedException nese personi qe nuk eshte admin tenton te lexoj faturat e tjera
     * @throws JWTDecodeException probleme me JWT
     * @throws NumberFormatException probleme me JWT
     */
    List<Bill> getByUser(Long userId, String requestJwt) throws UnauthorizedException, NumberFormatException, JWTDecodeException;

    /**
     * Merr faturat sipas statusit te pageses
     *
     * @param isPaid statusi i pageses
     * @return lista e faturave sipas statusit
     * @throws UnauthorizedException nese personi nuk edhte admin
     * @throws JWTDecodeException probleme me JWT
     * @throws NumberFormatException probleme me JWT
     */
    List<Bill> getByPaymentStatus(boolean isPaid, String requestJwt) throws UnauthorizedException, NumberFormatException, JWTDecodeException;

    /**
     * Shenon faturen si te paguar
     *
     * @param id ID e fature
     * @throws UnauthorizedException nese personi nuk edhte admin
     * @throws NotFoundException nese fatura me ID ne fjale nuk eksiston
     * @throws JWTDecodeException probleme me JWT
     * @throws NumberFormatException probleme me JWT
     */
    void markAsPaid(Long id, String requestJwt)throws UnauthorizedException, NumberFormatException, JWTDecodeException, NotFoundException;

    /**
     * Fshin faturen nga databaza
     *
     * @param id ID e fatures per fshirje
     * @throws UnauthorizedException nese personi nuk edhte admin
     * @throws NotFoundException nese nuk eksiston useri m eid perkatese
     * @throws JWTDecodeException probleme me JWT
     * @throws NumberFormatException probleme me JWT
     */
    void delete(Long id, String requestJwt) throws UnauthorizedException, NumberFormatException, JWTDecodeException, NotFoundException;

}
