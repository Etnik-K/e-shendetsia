package edu.unipr.eshendetsia.service.implementation;

import edu.unipr.eshendetsia.model.entity.Feedback;
import edu.unipr.eshendetsia.repository.FeedbackRepository;
import edu.unipr.eshendetsia.service.interfaces.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedbackServiceImplementation implements FeedbackService {

    private final FeedbackRepository feedbackRepository;

    @Autowired
    public FeedbackServiceImplementation(FeedbackRepository feedbackRepository) {
        this.feedbackRepository = feedbackRepository;
    }

    public Feedback save(Feedback feedback) {
        return feedbackRepository.save(feedback);
    }

    public List<Feedback> getByDoctorId(Long doctorId) {
        return feedbackRepository.findByDoctorId(doctorId);
    }

    public List<Feedback> getByUserId(Long userId) {
        return feedbackRepository.findByUserId(userId);
    }

    public void delete(Long id) {
        feedbackRepository.deleteById(id);
    }

}
