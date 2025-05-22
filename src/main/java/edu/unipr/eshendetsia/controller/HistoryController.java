package edu.unipr.eshendetsia.controller;

import com.auth0.jwt.exceptions.JWTVerificationException;
import edu.unipr.eshendetsia.exception.UnauthorizedException;
import edu.unipr.eshendetsia.http.request.body.SaveHistoryRequest;
import edu.unipr.eshendetsia.service.interfaces.HistoryService;
import edu.unipr.eshendetsia.http.response.ApiResponse;
import edu.unipr.eshendetsia.controller.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Kontrolluesi per menaxhimin e historikut te perdoruesit
 * Ofron API per ruajtjen dhe menaxhimin e te dhenave historike
 */
@RestController
@RequestMapping("/history")
public class HistoryController extends BaseController {

    private final HistoryService historyService;

    @Autowired
    public HistoryController(HistoryService historyService){
        this.historyService = historyService;
    }

    /**
     * Ruan historikun e ri te perdoruesit ne sistem
     *
     * @param historyRequest te dhenat e historikut per tu ruajtur
     * @return pergjigjen me historikun e ruajtur
     */
    @PostMapping("/save")
    public ResponseEntity<ApiResponse<String>> saveUserHistory(@RequestBody SaveHistoryRequest historyRequest){
        try{
            historyService.save(historyRequest.toHistory());
            return this.ok("Historiku u ruajt me sukses");
        } catch (UnauthorizedException | JWTVerificationException e) {
            return this.error("Nuk jeni i autorizuar", HttpStatus.UNAUTHORIZED );
        }
    }
}
