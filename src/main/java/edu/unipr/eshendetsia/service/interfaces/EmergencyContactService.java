package edu.unipr.eshendetsia.service.interfaces;

import edu.unipr.eshendetsia.model.EmergencyContact;

import java.util.List;

public interface EmergencyContactService {
    public EmergencyContact save(EmergencyContact contact);

    public List<EmergencyContact> getByUserId(Long userId);

    public void delete(Long id);
}
