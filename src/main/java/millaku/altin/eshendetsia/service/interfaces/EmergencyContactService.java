package millaku.altin.eshendetsia.service.interfaces;

import millaku.altin.eshendetsia.model.entity.EmergencyContact;

import java.util.List;

public interface EmergencyContactService {

    EmergencyContact save(EmergencyContact contact);

    List<EmergencyContact> getByUserId(Long userId);

    void delete(Long id);

}
