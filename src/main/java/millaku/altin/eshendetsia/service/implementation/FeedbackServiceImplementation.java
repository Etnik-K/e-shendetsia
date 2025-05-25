package millaku.altin.eshendetsia.service.implementation;

import millaku.altin.eshendetsia.model.entity.Feedback;
import millaku.altin.eshendetsia.repository.FeedbackRepository;
import millaku.altin.eshendetsia.service.interfaces.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementimi i sherbimit per menaxhimin e reagimeve
 * nga perdoruesit per doktoret
 */
@Service
public class FeedbackServiceImplementation implements FeedbackService {

    private final FeedbackRepository feedbackRepository;

    /**
     * Konstruktori i klases
     *
     * @param feedbackRepository repository per ruajtjen e reagimeve
     */
    @Autowired
    public FeedbackServiceImplementation(FeedbackRepository feedbackRepository) {
        this.feedbackRepository = feedbackRepository;
    }

    /**
     * Ruan nje reagim te ri ne sistem
     *
     * @param feedback reagimi qe do te ruhet
     * @return reagimi i ruajtur
     */
    public Feedback save(Feedback feedback) {
        return feedbackRepository.save(feedback);
    }

    /**
     * Merr te gjitha reagimet per nje doktor specifik
     *
     * @param doctorId identifikuesi i doktorit
     * @return lista e reagimeve
     */
    public List<Feedback> getByDoctorId(Long doctorId) {
        return feedbackRepository.findByDoctorId(doctorId);
    }

    /**
     * Merr te gjitha reagimet nga nje perdorues specifik
     *
     * @param userId identifikuesi i perdoruesit
     * @return lista e reagimeve
     */
    public List<Feedback> getByUserId(Long userId) {
        return feedbackRepository.findByUserId(userId);
    }

    /**
     * Fshin nje reagim nga sistemi
     *
     * @param id identifikuesi i reagimit per tu fshire
     */
    public void delete(Long id) {
        feedbackRepository.deleteById(id);
    }

}
