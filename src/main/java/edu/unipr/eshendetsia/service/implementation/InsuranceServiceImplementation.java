package edu.unipr.eshendetsia.service.implementation;

import edu.unipr.eshendetsia.model.entity.Insurance;
import edu.unipr.eshendetsia.repository.InsuranceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InsuranceServiceImplementation {
    private final InsuranceRepository insuranceRepository;

    @Autowired
    public InsuranceServiceImplementation(InsuranceRepository insuranceRepository) {
        this.insuranceRepository = insuranceRepository;
    }

    public Insurance save(Insurance insurance) {
        return insuranceRepository.save(insurance);
    }

    public List<Insurance> getByUserId(Long userId) {
        return insuranceRepository.findByUserId(userId);
    }

    public Insurance updateStatus(Long id, boolean active) {
        Insurance insurance = insuranceRepository.findById(id).orElse(null);
        if (insurance != null) {
            insurance.setActive(active);
            return insuranceRepository.save(insurance);
        }
        return null;
    }

    public void delete(Long id) {
        insuranceRepository.deleteById(id);
    }
}
