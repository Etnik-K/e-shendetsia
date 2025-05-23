package edu.unipr.eshendetsia.service.interfaces;

import edu.unipr.eshendetsia.model.entity.Insurance;

import java.util.List;

public interface InsuranceService {
    public Insurance save(Insurance insurance);

    public List<Insurance> getByUserId(Long userId);

    public Insurance updateStatus(Long id, boolean active);

    public void delete(Long id);
}
