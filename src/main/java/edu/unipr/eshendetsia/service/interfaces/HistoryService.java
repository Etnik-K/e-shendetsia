package edu.unipr.eshendetsia.service.interfaces;

import com.auth0.jwt.exceptions.JWTVerificationException;
import edu.unipr.eshendetsia.exception.concrete.NotFoundException;
import edu.unipr.eshendetsia.exception.concrete.UnauthorizedException;
import edu.unipr.eshendetsia.model.entity.History;

public interface HistoryService {
    /**
     * Ruan nje rekord te ri ne historine e sistemit
     *
     * @param history rekordi i historise per tu ruajtur
     * @throws UnauthorizedException    nese perdoruesi nuk ka te drejta
     * @throws JWTVerificationException nese ka problem me token
     */
    void save(History history, String authHeader) throws UnauthorizedException, JWTVerificationException, NotFoundException;
}
