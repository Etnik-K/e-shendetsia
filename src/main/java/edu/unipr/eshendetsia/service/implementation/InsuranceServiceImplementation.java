package edu.unipr.eshendetsia.service.implementation;

import edu.unipr.eshendetsia.model.entity.Insurance;
import edu.unipr.eshendetsia.repository.InsuranceRepository;
import edu.unipr.eshendetsia.service.interfaces.InsuranceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementimi i sherbimit te sigurimit
 * Menaxhon operacionet CRUD per sigurimet
 */
@Service
public class InsuranceServiceImplementation implements InsuranceService {
    private final InsuranceRepository insuranceRepository;

    /**
     * Konstruktori i klases
     *
     * @param insuranceRepository repository per sigurimet
     */
    @Autowired
    public InsuranceServiceImplementation(InsuranceRepository insuranceRepository) {
        this.insuranceRepository = insuranceRepository;
    }

    /**
     * Ruan nje sigurim te ri
     *
     * @param insurance sigurimi qe do te ruhet
     * @return sigurimi i ruajtur
     */
    public Insurance save(Insurance insurance) {
        return insuranceRepository.save(insurance);
    }

    /**
     * Merr te gjitha sigurimet e nje perdoruesi
     *
     * @param userId ID e perdoruesit
     * @return lista e sigurimeve
     */
    public List<Insurance> getByUserId(Long userId) {
        return insuranceRepository.findByUserId(userId);
    }

    /**
     * Perditeson statusin e nje sigurimi
     *
     * @param id     ID e sigurimit
     * @param active statusi i ri
     * @return sigurimi i perditesuar
     */
    public Insurance updateStatus(Long id, boolean active) {
        Insurance insurance = insuranceRepository.findById(id).orElse(null);
        if (insurance != null) {
            insurance.setActive(active);
            return insuranceRepository.save(insurance);
        }
        return null;
    }

    /**
     * Fshin nje sigurim
     *
     * @param id ID e sigurimit qe do te fshihet
     */
    public void delete(Long id) {
        insuranceRepository.deleteById(id);
    }
}
