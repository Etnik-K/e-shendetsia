package edu.unipr.eshendetsia.controller;

import edu.unipr.eshendetsia.model.entity.History;
import edu.unipr.eshendetsia.service.interfaces.HistoryService;
import edu.unipr.eshendetsia.http.response.ApiResponse;
import edu.unipr.eshendetsia.util.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
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
     * @param history te dhenat e historikut per tu ruajtur
     * @return pergjigjen me historikun e ruajtur
     */
    @PostMapping("/save")
    public ResponseEntity<ApiResponse<History>> saveUserHistory(@RequestBody History history){
        History saved = historyService.save(history);
        return ResponseEntity.ok(new ApiResponse<>(true, saved, "Gabim"));
    }
}
