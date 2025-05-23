package edu.unipr.eshendetsia.service.interfaces;

import com.auth0.jwt.exceptions.JWTDecodeException;
import edu.unipr.eshendetsia.exception.concrete.InternalServerErrorException;
import edu.unipr.eshendetsia.exception.concrete.NotFoundException;
import edu.unipr.eshendetsia.exception.concrete.UnauthorizedException;
import edu.unipr.eshendetsia.model.entity.Clinic;

import java.util.List;

public interface ClinicService {

    /**
     * Merr listen e te gjitha klinikave ne sistem.
     *
     * @param authToken tokeni i autentifikimit
     * @return lista e klinikave
     * @throws UnauthorizedException nese perdoruesi nuk eshte admin
     * @throws JWTDecodeException JWT jo-valid
     * @throws NumberFormatException JWT jo-valid     */
    List<Clinic> getAllClinics(String authToken) throws UnauthorizedException, JWTDecodeException, NumberFormatException;

    /**
     * Merr kliniken sipas ID-se.
     *
     * @param clinicId   ID e klinikes
     * @param authHeader tokeni i autentifikimit
     * @return klinika e kerkuar
     * @throws UnauthorizedException nese perdoruesi nuk eshte admin
     * @throws JWTDecodeException JWT jo-valid
     * @throws NumberFormatException JWT jo-valid
     * @throws NotFoundException nuk gjene user me id ne JWT
     */
    Clinic getClinicById(Long clinicId, String authHeader) throws UnauthorizedException, JWTDecodeException, NumberFormatException, NotFoundException ;

    /**
     * Ruan te dhenat e klinikes ne sistem.
     *
     * @param clinic     klinika per tu ruajtur
     * @param authHeader tokeni i autentifikimit
     * @throws UnauthorizedException nese perdoruesi nuk eshte admin
     * @throws JWTDecodeException JWT jo-valid
     * @throws NumberFormatException JWT jo-valid
     * @throws NotFoundException nuk gjene user me id ne JWT
     */
    void saveClinic(Clinic clinic, String authHeader) throws UnauthorizedException, JWTDecodeException, NumberFormatException, NotFoundException;

    /**
     * Perditeson te dhenat e klinikes.
     *
     * @param updateClinic   klinika me te dhenat e reja
     * @param authHeader     tokeni i autentifikimit
     * @throws UnauthorizedException nese perdoruesi nuk eshte admin
     * @throws JWTDecodeException JWT jo-valid
     * @throws NumberFormatException JWT jo-valid
     * @throws InternalServerErrorException nuk eksiston klinika
     * */
    void updateClinic(Clinic updateClinic, String authHeader) throws UnauthorizedException, JWTDecodeException, NumberFormatException, InternalServerErrorException;

    /**
     * Fshin kliniken nga sistemi.
     *
     * @param id         ID e klinikes per tu fshire
     * @param authHeader tokeni i autentifikimit
     * @throws UnauthorizedException nese perdoruesi nuk eshte admin
     * @throws JWTDecodeException JWT jo-valid
     * @throws NumberFormatException JWT jo-valid
     * @throws NotFoundException nuk gjene user me id ne JWT
     * @throws InternalServerErrorException nuk eksiston klinika
     */
    void deleteClinic(Long id, String authHeader) throws UnauthorizedException, NotFoundException, JWTDecodeException, NumberFormatException, InternalServerErrorException;
}
