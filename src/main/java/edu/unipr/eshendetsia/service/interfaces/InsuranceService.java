package edu.unipr.eshendetsia.service.interfaces;

import com.auth0.jwt.exceptions.JWTDecodeException;
import edu.unipr.eshendetsia.exception.concrete.NotFoundException;
import edu.unipr.eshendetsia.exception.concrete.UnauthorizedException;
import edu.unipr.eshendetsia.model.entity.Insurance;

import java.util.List;

public interface InsuranceService {

    /**
     * Ruan nje sigurim te ri
     *
     * @param insurance sigurimi qe do te ruhet
     * @throws UnauthorizedException Nese useri tenton te vendose insurance qe nuk i takon
     * @throws NumberFormatException probleme me JWT
     * @throws JWTDecodeException probleme me JWT
     */
    void save(Insurance insurance, String authHeader) throws UnauthorizedException, NumberFormatException, JWTDecodeException;

    /**
     * Merr te gjitha sigurimet e nje perdoruesi
     *
     * @param viewUserId ID e perdoruesit
     * @return lista e sigurimeve
     * @throws UnauthorizedException Nese useri tenton te lexoj insurance qe nuk i takon
     * @throws NumberFormatException probleme me JWT
     * @throws JWTDecodeException probleme me JWT
     */
    List<Insurance> getByUserId(Long viewUserId, String authHeader) throws UnauthorizedException, NumberFormatException, JWTDecodeException;

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
    void updateStatus(Long id, boolean active, String authHeader) throws NumberFormatException, JWTDecodeException, UnauthorizedException, NotFoundException;

    /**
     * Fshin nje sigurim
     *
     * @param id ID e sigurimit qe do te fshihet
     * @throws UnauthorizedException Ne qofte se nje jo-admin tenton te fshij gjendjen e nje insurance qe nuk i takon
     * @throws NumberFormatException probleme me JWT
     * @throws JWTDecodeException probleme me JWT
     */
    void delete(Long id, String authHeader) throws NumberFormatException, JWTDecodeException, UnauthorizedException;

}
