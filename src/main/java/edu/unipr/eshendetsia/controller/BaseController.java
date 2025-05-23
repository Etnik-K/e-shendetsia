package edu.unipr.eshendetsia.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


/**
 * Klasa baze qe perdoret per te menaxhuar pergjigjet e API-se.
 * Permban funksionalitete te perbashketa per te gjithe kontrollerat e tjere.
 * Ofron metoda ndihmese per te krijuar pergjigje te standartizuara te API.
 */
@RestController
@ResponseBody
public abstract class BaseController {

    /**
     * Kthen nje pergjigje te suksesshme me te dhenat e dhena
     *
     * @param data Te dhenat qe do te kthehen
     * @return Pergjigja e formatuar
     */
    protected <T> ResponseEntity<T> ok(T data) {
        return ResponseEntity.ok(data);
    }

}
