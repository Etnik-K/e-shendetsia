package edu.unipr.eshendetsia.service.interfaces;

import edu.unipr.eshendetsia.model.Referral;

import java.util.List;

public interface ReferralService {
    public Referral save(Referral referral);

    public List<Referral> getByPatient(Long patientId);
    public List<Referral> getByReceivingDoctor(Long doctorId) ;
    public void delete(Long id);
}
