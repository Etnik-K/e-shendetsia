package millaku.altin.eshendetsia.service.interfaces;

import millaku.altin.eshendetsia.model.entity.Referral;

import java.util.List;

public interface ReferralService {

    Referral save(Referral referral);

    List<Referral> getByPatient(Long patientId);

    List<Referral> getByReceivingDoctor(Long doctorId) ;

    void delete(Long id);

}
