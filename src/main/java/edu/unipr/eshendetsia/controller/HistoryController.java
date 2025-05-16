package edu.unipr.eshendetsia.controller;

import edu.unipr.eshendetsia.model.entity.History;
import edu.unipr.eshendetsia.service.interfaces.HistoryService;
import edu.unipr.eshendetsia.http.response.ApiResponse;
import edu.unipr.eshendetsia.util.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/history")
public class HistoryController extends BaseController {

    private final HistoryService historyService;

    @Autowired
    public HistoryController(HistoryService historyService){
        this.historyService = historyService;
    }

    @PostMapping("/save")
    public ResponseEntity<ApiResponse<History>> saveUserHistory(@RequestBody History history){
        History saved = historyService.save(history);
        return ResponseEntity.ok(new ApiResponse<>(true, saved, "Gabim"));
    }
}
