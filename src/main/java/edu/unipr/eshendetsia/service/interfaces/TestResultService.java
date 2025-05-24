package edu.unipr.eshendetsia.service.interfaces;

import edu.unipr.eshendetsia.model.entity.TestResult;

import java.util.List;

public interface TestResultService {

    TestResult save(TestResult testResult, String requestJwt);

    List<TestResult> getByUserId(Long userId, String requestJwt);

    List<TestResult> getByDoctorId(Long doctorId, String requestJwt);

    void delete(Long id);

}
