package edu.unipr.eshendetsia.service.implementation;

import edu.unipr.eshendetsia.model.entity.TestResult;
import edu.unipr.eshendetsia.repository.TestResultRepository;
import edu.unipr.eshendetsia.service.interfaces.TestResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementimi i sherbimit per rezultatet e testeve mjekesore
 * Sherben per ruajtjen dhe marrjen e rezultateve nga databaza
 */
@Service
public class TestResultServiceImplementation implements TestResultService {
    private final TestResultRepository testResultRepository;

    /**
     * Konstruktori i klases
     *
     * @param testResultRepository repository per rezultatet e testeve
     */
    @Autowired
    public TestResultServiceImplementation(TestResultRepository testResultRepository) {
        this.testResultRepository = testResultRepository;
    }

    /**
     * Ruan rezultatin e testit ne databaze
     *
     * @param testResult rezultati i testit per tu ruajtur
     * @return rezultati i ruajtur
     */
    public TestResult save(TestResult testResult){
        return testResultRepository.save(testResult);
    }

    /**
     * Merr rezultatet e testeve per nje pacient
     *
     * @param userId ID e pacientit
     * @return lista e rezultateve te testeve
     */
    public List<TestResult> getByUserId(Long userId, String requestJwt) {
        return testResultRepository.findByUserId(userId);
    }

    /**
     * Merr rezultatet e testeve per nje doktor
     *
     * @param doctorId ID e doktorit
     * @return lista e rezultateve te testeve
     */
    public List<TestResult> getByDoctorId(Long doctorId, String requestJwt) {
        return testResultRepository.findByDoctorId(doctorId);
    }

    /**
     * Fshin rezultatin e testit nga databaza
     *
     * @param id ID e rezultatit per tu fshire
     */
    public void delete(Long id, String requestJwt) {
        testResultRepository.deleteById(id);
    }
}
