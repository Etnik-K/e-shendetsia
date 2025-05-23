package edu.unipr.eshendetsia.service.interfaces;

import edu.unipr.eshendetsia.model.entity.TestResult;

import java.util.List;

public interface TestResultService {

    void save(TestResult testResult);

    List<TestResult> getByUserId(Long userId);

    List<TestResult> getByDoctorId(Long doctorId);

    void delete(Long id);

}
