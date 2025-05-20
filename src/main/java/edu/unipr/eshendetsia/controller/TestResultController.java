package edu.unipr.eshendetsia.controller;

import com.auth0.jwt.exceptions.JWTVerificationException;
import edu.unipr.eshendetsia.controller.base.BaseController;
import edu.unipr.eshendetsia.exception.UnauthorizedException;
import edu.unipr.eshendetsia.http.response.ApiResponse;
import edu.unipr.eshendetsia.model.entity.TestResult;
import edu.unipr.eshendetsia.service.interfaces.TestResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * Kontrollues per rezultatet e testeve mjekesore
 * Menaxhon krijimin, marrjen dhe fshirjen e rezultateve te testeve
 */
@RestController
@RequestMapping("/test_results")
public class TestResultController extends BaseController {

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
    public ResponseEntity<ApiResponse<TestResult>> create(@RequestBody TestResult testResult) {
        return this.ok(testResultService.save(testResult));
    }

    /**
     * Merr te gjitha rezultatet e testeve per nje perdorues
     *
     * @param userId ID e perdoruesit
     * @return lista e rezultateve te testeve
     */
    @GetMapping("/user/{userId}")
    public ResponseEntity<ApiResponse<List<TestResult>>> getByUser(@PathVariable Long userId) {
        return this.ok(testResultService.getByUserId(userId));
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
    public ResponseEntity<ApiResponse<String>> delete(@PathVariable Long id) {
        try{
            this.testResultService.delete(id);
            return this.ok("Rezultati i testit u fshi me sukses");
        } catch (JWTVerificationException | UnauthorizedException exception) {
            return this.error("Nuk jeni i autorizuar", HttpStatus.UNAUTHORIZED);
        }
    }

}
