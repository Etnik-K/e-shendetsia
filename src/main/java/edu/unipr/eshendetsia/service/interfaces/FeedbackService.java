package edu.unipr.eshendetsia.service.interfaces;

import edu.unipr.eshendetsia.model.entity.Feedback;

import java.util.List;

public interface FeedbackService {

    public Feedback save(Feedback feedback);

    public List<Feedback> getByDoctorId(Long doctorId);

    public List<Feedback> getByUserId(Long userId);

    public void delete(Long id);
}
