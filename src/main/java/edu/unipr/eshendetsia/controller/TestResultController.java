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
public class TestResultController {

    @Autowired
    private final TestResultServiceImplementation testResultServiceImplementation;
    public TestResultController(TestResultServiceImplementation testResultServiceImplementation) {
        this.testResultServiceImplementation = testResultServiceImplementation;
    }
    @PostMapping
    public ResponseEntity<TestResult> create(@RequestBody TestResult testResult) {
        return ResponseEntity.ok(testResultServiceImplementation.save(testResult));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<TestResult>> getByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(testResultServiceImplementation.getByUserId(userId));
    }

    @GetMapping("/doctor/{doctorId}")
    public ResponseEntity<List<TestResult>> getByDoctor(@PathVariable Long doctorId) {
        return ResponseEntity.ok(testResultServiceImplementation.getByDoctorId(doctorId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        testResultServiceImplementation.delete(id);
        return ResponseEntity.noContent().build();
    }

}
