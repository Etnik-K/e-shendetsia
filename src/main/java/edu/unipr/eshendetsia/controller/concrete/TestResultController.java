package edu.unipr.eshendetsia.controller.concrete;

import edu.unipr.eshendetsia.controller.BaseController;
import edu.unipr.eshendetsia.http.request.body.CreateTestResultRequest;
import edu.unipr.eshendetsia.model.entity.TestResult;
import edu.unipr.eshendetsia.service.interfaces.TestResultService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * Kontrollues per rezultatet e testeve mjekesore
 * Menaxhon krijimin, marrjen dhe fshirjen e rezultateve te testeve
 */
@AllArgsConstructor
@RestController
@RequestMapping("/test_results")
public class TestResultController extends BaseController {

    private final TestResultService testResultService;

    /**
     * Krijon nje rezultat te ri testi
     *
     * @param testResultRequest rezultati i testit per tu ruajtur
     * @return rezultati i testit i ruajtur
     */
    @PostMapping
    public ResponseEntity<String> create(@RequestBody CreateTestResultRequest testResultRequest, @RequestHeader("Authorization") String authHeader) {
        this.testResultService.save(testResultRequest.toTestResult(), authHeader);
        return this.ok("Rezultati i testit u ruajt me sukses");
    }

    /**
     * Merr te gjitha rezultatet e testeve per nje perdorues
     *
     * @param userId ID e perdoruesit
     * @return lista e rezultateve te testeve
     */
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<TestResult>> getByUser(@PathVariable Long userId, @RequestHeader("Authorization") String authHeader) {
        return this.ok(testResultService.getByUserId(userId, authHeader));
    }

    /**
     * Merr te gjitha rezultatet e testeve per nje doktor
     *
     * @param doctorId ID e doktorit
     * @return lista e rezultateve te testeve
     */
    @GetMapping("/doctor/{doctorId}")
    public ResponseEntity<List<TestResult>> getByDoctor(@PathVariable Long doctorId, @RequestHeader("Authorization") String authHeader) {
        return ResponseEntity.ok(testResultService.getByDoctorId(doctorId, authHeader));
    }

    /**
     * Fshin nje rezultat testi
     *
     * @param id ID e rezultatit te testit per tu fshire
     * @return pergjigje bosh
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        this.testResultService.delete(id);
        return this.ok("Rezultati i testit u fshi me sukses");
    }

}
