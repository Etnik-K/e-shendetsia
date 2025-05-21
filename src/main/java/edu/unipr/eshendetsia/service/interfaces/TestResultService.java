package edu.unipr.eshendetsia.service.interfaces;

import edu.unipr.eshendetsia.model.TestResult;

import java.util.List;

public interface TestResultService {
    public TestResult save(TestResult testResult);
    public List<TestResult> getByUserId(Long userId);
    public List<TestResult> getByDoctorId(Long doctorId);
    public void delete(Long id);
}
