package edu.unipr.eshendetsia.controller.base;

import edu.unipr.eshendetsia.http.response.ApiResponse;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
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
    protected <T> ResponseEntity<ApiResponse<T>> ok(T data) {
        return ResponseEntity.ok(new ApiResponse<>(true, data, null));
    }

    /**
     * Kthen nje pergjigje gabimi me mesazhin dhe kodin e dhene
     *
     * @param errorMessage Mesazhi i gabimit
     * @param code         Kodi i statusit HTTP
     * @return Pergjigja e formatuar me gabimin
     */
    protected <T> ResponseEntity<ApiResponse<T>> error(String errorMessage, HttpStatusCode code) {
        return ResponseEntity.status(code)
                .body(new ApiResponse<>(false, null, errorMessage));
    }

}
