package edu.unipr.eshendetsia.service.implementation;

import edu.unipr.eshendetsia.model.entity.TestResult;
import edu.unipr.eshendetsia.repository.TestResultRepository;
import edu.unipr.eshendetsia.service.interfaces.TestResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestResultServiceImplementation implements TestResultService {
    private final TestResultRepository testResultRepository;

    @Autowired
    public TestResultServiceImplementation(TestResultRepository testResultRepository) {
        this.testResultRepository = testResultRepository;
    }

    public TestResult save(TestResult testResult){
        return testResultRepository.save(testResult);
    }

    public List<TestResult> getByUserId(Long userId) {
        return testResultRepository.findByUserId(userId);
    }

    public List<TestResult> getByDoctorId(Long doctorId) {
        return testResultRepository.findByDoctorId(doctorId);
    }

    public void delete(Long id) {
        testResultRepository.deleteById(id);
    }
}
