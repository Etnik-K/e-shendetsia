package edu.unipr.eshendetsia.controller;

import edu.unipr.eshendetsia.model.entity.TestResult;
import edu.unipr.eshendetsia.repository.TestResultRepository;
import edu.unipr.eshendetsia.service.implementation.TestResultServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/test_results")
/**
 * Kontrollues per rezultatet e testeve mjekesore
 * Menaxhon krijimin, marrjen dhe fshirjen e rezultateve te testeve
 */
public class TestResultController {

    @Autowired
    private final TestResultServiceImplementation testResultServiceImplementation;
    public TestResultController(TestResultServiceImplementation testResultServiceImplementation) {
        this.testResultServiceImplementation = testResultServiceImplementation;
    }

    /**
     * Krijon nje rezultat te ri testi
     *
     * @param testResult rezultati i testit per tu ruajtur
     * @return rezultati i testit i ruajtur
     */
    @PostMapping
    public ResponseEntity<TestResult> create(@RequestBody TestResult testResult) {
        return ResponseEntity.ok(testResultServiceImplementation.save(testResult));
    }

    /**
     * Merr te gjitha rezultatet e testeve per nje perdorues
     *
     * @param userId ID e perdoruesit
     * @return lista e rezultateve te testeve
     */
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<TestResult>> getByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(testResultServiceImplementation.getByUserId(userId));
    }

    /**
     * Merr te gjitha rezultatet e testeve per nje doktor
     *
     * @param doctorId ID e doktorit
     * @return lista e rezultateve te testeve
     */
    @GetMapping("/doctor/{doctorId}")
    public ResponseEntity<List<TestResult>> getByDoctor(@PathVariable Long doctorId) {
        return ResponseEntity.ok(testResultServiceImplementation.getByDoctorId(doctorId));
    }

    /**
     * Fshin nje rezultat testi
     *
     * @param id ID e rezultatit te testit per tu fshire
     * @return pergjigje bosh
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        testResultServiceImplementation.delete(id);
        return ResponseEntity.noContent().build();
    }

}
