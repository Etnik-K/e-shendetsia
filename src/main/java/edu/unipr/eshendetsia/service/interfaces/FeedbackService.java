package edu.unipr.eshendetsia.service.interfaces;

import edu.unipr.eshendetsia.model.entity.Feedback;

import java.util.List;

public interface FeedbackService {

    Feedback save(Feedback feedback);

    List<Feedback> getByDoctorId(Long doctorId);

    List<Feedback> getByUserId(Long userId);

    void delete(Long id);

}
