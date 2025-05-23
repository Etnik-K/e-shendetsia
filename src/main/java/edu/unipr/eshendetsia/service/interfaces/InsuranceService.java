package edu.unipr.eshendetsia.service.interfaces;

import edu.unipr.eshendetsia.model.entity.Insurance;

import java.util.List;

public interface InsuranceService {

    Insurance save(Insurance insurance);

    List<Insurance> getByUserId(Long userId);

    Insurance updateStatus(Long id, boolean active);

    void delete(Long id);

}
