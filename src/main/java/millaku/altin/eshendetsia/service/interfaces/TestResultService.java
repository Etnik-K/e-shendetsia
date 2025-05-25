package millaku.altin.eshendetsia.service.interfaces;

import millaku.altin.eshendetsia.model.entity.TestResult;

import java.util.List;

public interface TestResultService {

    TestResult save(TestResult testResult);

    List<TestResult> getByUserId(Long userId);

    List<TestResult> getByDoctorId(Long doctorId);

    void delete(Long id);

}
