package edu.unipr.eshendetsia.controller;

import edu.unipr.eshendetsia.model.entity.TestResult;
import edu.unipr.eshendetsia.service.interfaces.TestResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * Kontrollues per rezultatet e testeve mjekesore
 * Menaxhon krijimin, marrjen dhe fshirjen e rezultateve te testeve
 */
@RestController
@RequestMapping("/test_results")
public class TestResultController {

    private final TestResultService testResultService;

    @Autowired
    public TestResultController(TestResultService testResultService) {
        this.testResultService = testResultService;
    }

    /**
     * Krijon nje rezultat te ri testi
     *
     * @param testResult rezultati i testit per tu ruajtur
     * @return rezultati i testit i ruajtur
     */
    @PostMapping
    public ResponseEntity<TestResult> create(@RequestBody TestResult testResult) {
        return ResponseEntity.ok(testResultService.save(testResult));
    }

    /**
     * Merr te gjitha rezultatet e testeve per nje perdorues
     *
     * @param userId ID e perdoruesit
     * @return lista e rezultateve te testeve
     */
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<TestResult>> getByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(testResultService.getByUserId(userId));
    }

    /**
     * Merr te gjitha rezultatet e testeve per nje doktor
     *
     * @param doctorId ID e doktorit
     * @return lista e rezultateve te testeve
     */
    @GetMapping("/doctor/{doctorId}")
    public ResponseEntity<List<TestResult>> getByDoctor(@PathVariable Long doctorId) {
        return ResponseEntity.ok(testResultService.getByDoctorId(doctorId));
    }

    /**
     * Fshin nje rezultat testi
     *
     * @param id ID e rezultatit te testit per tu fshire
     * @return pergjigje bosh
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        testResultService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
